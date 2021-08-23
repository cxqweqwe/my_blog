package com.fang.backgroundapi.common;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/18 16:40
 **/
public enum ResponseCode {


    SUCCESS(2000,"操作成功"),
    UN_LOGIN(4001,"您还没有登录或者您之前登录已失效"),
    EMPTY_ACCOUNT(4000,"用户名不存在"),
    EMPTY_USERNAME(4000,"用户名不能为空"),
    EMPTY_PASSWORD(4000,"密码不能为空"),
    ERROR_PASSWORD(4000,"密码错误"),
    LOGIN_FAIL(4001,"登录失败"),
    UNAUTHORIZED(403,"对不起，您没有权限"),
    NOT_FOUND(404,"未查询到数据"),


    // 错误
    LOGIN_ERROR(5001,"登录出错，请检查用户名、密码。若确定检查无误，请联系管理人员"),
    AUTHENTICATION_ERROR(5002,"验证令牌出错，您的令牌可能失效，请重新登录"),
    DATA_FORMAT_TYPE_ERROR(5003,"数据格式类型出错"),
    ERROR(5000,"服务出错,操作失败");


    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
