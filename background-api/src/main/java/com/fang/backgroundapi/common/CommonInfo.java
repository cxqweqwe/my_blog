package com.fang.backgroundapi.common;

/**
 * @author Bernie_fang
 * @Description: 存放一些全局通用的变量
 * @create 2021/8/18 22:21
 **/
public class CommonInfo {

    /**
     * 6个小时，毫秒数
     */
    public static final Integer EXPIRATION_TIME_MILLISECOND = 6 * 60 * 60 * 1000;
    // public static final Integer EXPIRATION_TIME_MILLISECOND = 10 * 1000;//10秒，测试用的

    /**
     * 6个小时，秒数
     */
    public static final Integer EXPIRATION_TIME_SECOND = 6 * 60 * 60;

    /**
     *  登录用户的标识头
     */
    public static final String SYS_USER = "login_";

    /**
     *  记录邮箱发送验证码-标识头
     */
    public static final String EMAIL_CODE = "email_code_";

    /**
     * 记录最近请求的邮箱-标识头
     */
    public static final String EMAIL_RECENT_REQUEST = "recent_email_";

    /**
     *  记录邮箱发送验证码-标识头
     */
    public static final String PHONE_CODE = "phone_code_";

    /**
     * 记录最近请求的邮箱-标识头
     */
    public static final String PHONE_RECENT_REQUEST = "recent_phone_";


}
