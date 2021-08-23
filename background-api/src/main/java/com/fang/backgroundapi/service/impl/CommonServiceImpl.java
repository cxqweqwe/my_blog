package com.fang.backgroundapi.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.fang.backgroundapi.common.CommonInfo;
import com.fang.backgroundapi.common.ResponseCode;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.pojo.DO.SystemSettings;
import com.fang.backgroundapi.shiro.JWTToken;
import com.fang.backgroundapi.utils.JWTUtil;
import com.fang.backgroundapi.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author Bernie_fang
 * @Description: 这是一个通用服务类，没有接口
 * @create 2021/8/18 16:47
 **/
@Slf4j
@Service
public class CommonServiceImpl {

    @Autowired
    private SysUsersServiceImpl sysUsersService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SystemSettingsServiceImpl settingsService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    public String emailFrom;

    /**
     * @Description: 用户根据用户名和密码登录
     * @Author: Bernie_fang
     * @Since: 2021/8/18 17:30
     * @param username:
     * @param password:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse loginByUsername(String username, String password, HttpServletRequest request, HttpServletResponse response) throws MyException {
        username = username.trim();
        password = password.trim();
        // 判空
        if (StringUtils.isEmpty(username)) {
            throw new MyException(ResponseCode.EMPTY_USERNAME.getDesc(), ResponseCode.EMPTY_USERNAME.getCode());
        }
        if (StringUtils.isEmpty(password)) {
            throw new MyException(ResponseCode.EMPTY_PASSWORD.getDesc(), ResponseCode.EMPTY_PASSWORD.getCode());
        }
        SysUsers sysUser = sysUsersService.findUserByUsername(username);

        if (sysUser == null) {
            throw new MyException(ResponseCode.EMPTY_ACCOUNT.getDesc(), ResponseCode.EMPTY_ACCOUNT.getCode());
        }

        // String sha1Password = PasswordUtil.sha1Encode(password); //暂时关闭，后面记得开启
        if (!sysUser.getPassword().equals(password)) {
            // 密码错误
            throw new MyException(ResponseCode.ERROR_PASSWORD.getDesc(), ResponseCode.ERROR_PASSWORD.getCode());
        }
        String token = new String();
        try {
            //生成签名
            token = JWTUtil.signToToken(sysUser);
            //构造JWT Token
            JWTToken jwtToken = new JWTToken(token);
            //shiro安全框架：获取subject对象
            Subject subject = SecurityUtils.getSubject();
            //设置token
            subject.login(jwtToken);
        } catch (Exception e) {
            //发生异常，说明登录失败
            return ServerResponse.createLoginFail("登录出错，请检查用户名、密码");
        }
        redisUtils.set(CommonInfo.SYS_USER+ sysUser.getAuthorId(),sysUser,CommonInfo.EXPIRATION_TIME_SECOND);
        // TODO： 设置请求头的token
        response.setHeader("Authorization", token); //放到信息头部
        return ServerResponse.success();
    }

    /**
     * @Description: 退出登录接口
     * @Author: Bernie_fang
     * @Since: 2021/8/22 16:49
     * @param request:
     * @param response:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse loginOut(HttpServletRequest request, HttpServletResponse response) {String authorization = request.getHeader("Authorization");
        String authorId = JWTUtil.getAuthorId(authorization);

        request.removeAttribute("Authorization");//移除token
        redisUtils.del(CommonInfo.SYS_USER+ authorId);
        return ServerResponse.success();
    }

    /**
     * @Description: 检擦是否能发送验证码。获取验证码并发送到指定邮箱
     * @Author: Bernie_fang
     * @Since: 2021/8/23 14:15
     * @param email:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse getEmailCode(String email) {
        SystemSettings settings = settingsService.findSystemSetting("allow_email");
        if (settings.getAllowOrNot() == 0){
            // 不允许使用邮箱
            return ServerResponse.error(400, "此功能暂时关闭", null);
        }
        if (redisUtils.hasKey(CommonInfo.EMAIL_RECENT_REQUEST + email)){
            // 存在说明不久之前请求过
            return ServerResponse.error(400, "您的操作太快了，请稍后重试", null);
        }
        String numbers = RandomUtil.randomNumbers(6);//随机生成6位数号码
        redisUtils.set(CommonInfo.EMAIL_RECENT_REQUEST + email, email, 60); // 保存邮箱1分钟，1分钟内不允许重新请求
        redisUtils.set(CommonInfo.EMAIL_CODE + email, numbers, 5 * 60);     // 保验证码5分钟
        sendEmailCode(email,numbers);//调用发送邮件
        return ServerResponse.success("邮件已发送至邮箱[ + " + email + "],请稍后查收");
    }

    /**
     * @Description: 发送验证码到邮箱封装方法
     * @Author: Bernie_fang
     * @Since: 2021/8/23 14:14
     * @param email: 发送对象邮箱
     * @param code: 验证码
     * @return: void
     **/
    @Async
    public void sendEmailCode(String email, String code) {
        DateTime expireTime = DateUtil.offsetMinute(new Date(), 5);//5分钟过期
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                    true);
            // 设置渲染到html页面对应的值
            Context context = new Context();
            // context.setVariable("OJ_NAME", "ojName");
            // context.setVariable("OJ_SHORT_NAME", "fang-test");
            // context.setVariable("OJ_UR", "ojAddr");
            // context.setVariable("EMAIL_BACKGROUND_IMG", "ojEmailBg");
            context.setVariable("CODE", code);
            context.setVariable("EXPIRE_TIME", expireTime.toString());

            //利用模板引擎加载html文件进行渲染并生成对应的字符串
            String emailContent = templateEngine.process("emailTemplate", context);
            // 设置邮件标题
            mimeMessageHelper.setSubject("来fangweb个人博客-论坛网站邮件");
            mimeMessageHelper.setText(emailContent, true);
            // 收件人
            mimeMessageHelper.setTo(email);
            // 发送人
            mimeMessageHelper.setFrom(emailFrom);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("用户注册的邮件任务发生异常------------>{}" + e.getMessage());
        }
    }

    /**
     * @Description: 检擦是否能发送验证码。获取验证码并发送到指定手机。功能暂时不完善。不可用
     * @Author: Bernie_fang
     * @Since: 2021/8/23 14:18
     * @param phone:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse getPhoneCode(String phone) throws Exception {
        SystemSettings settings = settingsService.findSystemSetting("allow_phone");//查询是否允许使用手机验证码
        if (settings.getAllowOrNot() == 0){
            // 不允许手机验证码
            return ServerResponse.error(400, "此功能暂时关闭", null);
        }
        if (redisUtils.hasKey(CommonInfo.PHONE_RECENT_REQUEST + phone)){
            // 存在说明不久之前请求过
            return ServerResponse.error(400, "您的操作太快了，请稍后重试", null);
        }
        String numbers = RandomUtil.randomNumbers(6);//随机生成6位数号码
        redisUtils.set(CommonInfo.PHONE_RECENT_REQUEST + phone, phone, 60); // 保存手机1分钟，1分钟内不允许重新请求
        redisUtils.set(CommonInfo.PHONE_CODE + phone, numbers, 5 * 60);     // 保验证码5分钟
        this.sendPhoneCode(phone,numbers);
        return null;
    }

    /**
     * @Description: 发送验证码到手机
     * @Author: Bernie_fang
     * @Since: 2021/8/23 14:10
     * @param phoneNumber: 发送手机对象
     * @param code: 验证码
     * @return: java.lang.String
     **/
    public String sendPhoneCode(String phoneNumber, String code) throws Exception {
        // 需要更改
        Client client = createClient("LTAI5tCkfddZpbXhrjuoAmdu", "UvczWgQqxFjHm9aFnrO1Zqr3OS0aUV");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumber)
                .setSignName("大学导航")//签名不合法(不存在或被拉黑)
                .setTemplateCode("SMS_217965723")
                .setTemplateParam("{\"code\":\"" + code + "\"}");
        // 复制代码运行请自行打印 API 的返回值
        // client.sendSms(sendSmsRequest);
        SendSmsResponse response = client.sendSms(sendSmsRequest);
        return response.getBody().getMessage();
    }

    /**
     * @Description: 阿里云的初始化Client方法，官方提供
     * @Author: Bernie_fang
     * @Since: 2021/8/23 14:10
     * @param accessKeyId:
     * @param accessKeySecret:
     * @return: com.aliyun.dysmsapi20170525.Client
     **/
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }



}
