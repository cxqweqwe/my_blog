package com.fang.backgroundapi.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author Bernie_fang
 * @Description: 专门做异步操作的Service
 * @create 2021/9/15 10:56
 **/
@Service
@Async
public class AsyncService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    public String emailFrom;

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



}
