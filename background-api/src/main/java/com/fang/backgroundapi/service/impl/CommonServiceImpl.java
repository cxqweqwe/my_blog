package com.fang.backgroundapi.service.impl;

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
import com.fang.backgroundapi.pojo.DO.UserInfo;
import com.fang.backgroundapi.pojo.DTO.RegisterDTO;
import com.fang.backgroundapi.shiro.JWTToken;
import com.fang.backgroundapi.typehandler.Encrypt;
import com.fang.backgroundapi.utils.JWTUtil;
import com.fang.backgroundapi.utils.PasswordUtil;
import com.fang.backgroundapi.utils.RedisUtils;
import com.fang.backgroundapi.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

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
    private AsyncService asyncService;

    @Autowired
    private UserInfoServiceImpl userInfoService;


    /**
     * @param username:
     * @param password:
     * @Description: 用户根据用户名和密码登录
     * @Author: Bernie_fang
     * @Since: 2021/8/18 17:30
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
        String encodePassword = PasswordUtil.sha1Encode(password); //暂时关闭，后面记得开启
        if (!sysUser.getPassword().equals(encodePassword)) {
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
        redisUtils.set(CommonInfo.SYS_USER + sysUser.getAuthorId(), sysUser, CommonInfo.EXPIRATION_TIME_SECOND);
        response.setHeader("Authorization", token); //放到信息头部
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("authorId", sysUser.getAuthorId());
        UserInfo info = userInfoService.findUserInfoByAuthorId(sysUser.getAuthorId());
        map.put("nickName", info.getNickName());
        map.put("avatarPath", info.getAvatarPath());
        return ServerResponse.success(map);
    }

    /**
     * @param request:
     * @param response:
     * @Description: 退出登录接口
     * @Author: Bernie_fang
     * @Since: 2021/8/22 16:49
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse loginOut(HttpServletRequest request, HttpServletResponse response) {
        String authorization = request.getHeader("Authorization");
        String authorId = JWTUtil.getAuthorId(authorization);

        request.removeAttribute("Authorization");//移除token
        redisUtils.del(CommonInfo.SYS_USER + authorId);
        return ServerResponse.success();
    }

    /**
     * @param email:
     * @Description: 检擦是否能发送验证码。获取验证码并发送到指定邮箱
     * @Author: Bernie_fang
     * @Since: 2021/8/23 14:15
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse getEmailCode(String email) {
        SystemSettings settings = settingsService.findSystemSetting("allow_email");
        if (settings.getAllowOrNot() == 0) {
            // 不允许使用邮箱
            return ServerResponse.error(400, "此功能暂时关闭", null);
        }
        if (redisUtils.hasKey(CommonInfo.EMAIL_RECENT_REQUEST + email)) {
            // 存在说明不久之前请求过
            return ServerResponse.error(400, "您的操作太快了，请稍后重试", null);
        }
        String numbers = RandomUtil.randomNumbers(6);//随机生成6位数号码
        redisUtils.set(CommonInfo.EMAIL_RECENT_REQUEST + email, email, 60); // 保存邮箱1分钟，1分钟内不允许重新请求
        redisUtils.set(CommonInfo.EMAIL_CODE + email, numbers, 5 * 60);     // 保验证码5分钟
        asyncService.sendEmailCode(email, numbers);//调用发送邮件
        return ServerResponse.success("邮件已发送至邮箱[ " + email + " ],请稍后查收");
    }

    /**
     * @param phone:
     * @Description: 检擦是否能发送验证码。获取验证码并发送到指定手机。功能暂时不完善。不可用
     * @Author: Bernie_fang
     * @Since: 2021/8/23 14:18
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse getPhoneCode(String phone) throws Exception {
        SystemSettings settings = settingsService.findSystemSetting("allow_phone");//查询是否允许使用手机验证码
        if (settings.getAllowOrNot() == 0) {
            // 不允许手机验证码
            return ServerResponse.error(400, "此功能暂时关闭", null);
        }
        if (redisUtils.hasKey(CommonInfo.PHONE_RECENT_REQUEST + phone)) {
            // 存在说明不久之前请求过
            return ServerResponse.error(400, "您的操作太快了，请稍后重试", null);
        }
        String numbers = RandomUtil.randomNumbers(6);//随机生成6位数号码
        redisUtils.set(CommonInfo.PHONE_RECENT_REQUEST + phone, phone, 60); // 保存手机1分钟，1分钟内不允许重新请求
        redisUtils.set(CommonInfo.PHONE_CODE + phone, numbers, 5 * 60);     // 保验证码5分钟
        this.sendPhoneCode(phone, numbers);
        return null;
    }

    /**
     * @param phoneNumber: 发送手机对象
     * @param code:        验证码
     * @Description: 发送验证码到手机
     * @Author: Bernie_fang
     * @Since: 2021/8/23 14:10
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
     * @param accessKeyId:
     * @param accessKeySecret:
     * @Description: 阿里云的初始化Client方法，官方提供
     * @Author: Bernie_fang
     * @Since: 2021/8/23 14:10
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

    /**
     * @param registerDTO:
     * @Description: 向两个表操作，需要开启事务
     * @Author: Bernie_fang
     * @Since: 2021/9/15 13:46
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    public ServerResponse registe(RegisterDTO registerDTO) {
        // 做数据校验
        String username = registerDTO.getUsername().trim();
        String password = registerDTO.getPassword().trim();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ServerResponse.error(ResponseCode.DATA_FORMAT_TYPE_ERROR.getCode(), ResponseCode.DATA_FORMAT_TYPE_ERROR.getDesc(),
                    "用户名和密码均不能为空");
        }
        // UserInfo infoByEmail = userInfoService.findUserInfoByEmail(registerDTO.getEmail());
        UserInfo infoByEmail = userInfoService.findUserInfoByEmail(new Encrypt(registerDTO.getEmail()));
        if (infoByEmail != null) {
            return ServerResponse.error(4000, "该邮箱已经绑定账号，请更换邮箱", null);
        }
        String email = (String) redisUtils.get(CommonInfo.EMAIL_CODE + registerDTO.getEmail());//拿到之前报错的验证码
        if (StringUtils.isEmpty(email)) {
            return ServerResponse.error(4000, "验证码不存在或已失效，请重新获取", null);
        }
        if (!email.equals(registerDTO.getEmailCaptcha())) {
            return ServerResponse.error(4000, "验证码错误", null);
        }
        String encodePwd = PasswordUtil.sha1Encode(password);//加密密码

        Long id = SnowflakeIdWorker.generateId();
        SysUsers users = new SysUsers();
        users.setAuthorId(id.toString());
        users.setPassword(encodePwd);
        users.setUsername(registerDTO.getUsername());
        sysUsersService.insertUser(users);

        // 往用户信息表添加信息      空信息即可
        UserInfo userInfo = new UserInfo();
        userInfo.setAuthorId(id.toString());
        userInfo.setNickName(id.toString());
        userInfo.setEmail(new Encrypt(registerDTO.getEmail()));
        userInfoService.insertUserInfo(userInfo);
        return ServerResponse.success(2000, "成功注册！", null);// TODO: 需要返回数据，暂时先不处理
    }


}
