package com.fang.backgroundapi.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Bernie_fang
 * @Description: 统一返回数据封装类
 * @create 2021/8/18 16:40
 **/

@Data
public class ServerResponse {

    private int status;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)//即如果加该注解的字段为null,那么就不序列化这个字段了。
    private Object data;
    // @JsonInclude(JsonInclude.Include.NON_NULL)//即如果加该注解的字段为null,那么就不序列化这个字段了。
    // private String errorMessage;//如果服务器错误，携带错误信息

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


    //成功操作
    public static ServerResponse success() {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }

    public static ServerResponse successWithMessage(String msg) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static ServerResponse success(Object data) {
        return new ServerResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public static ServerResponse success(int code, String message, Object data) {
        return new ServerResponse(code, message, data);
    }

    //操作失败
    public static ServerResponse error(Object data) {
        return new ServerResponse(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc(), data);
    }

    // /**
    //  * @Description: 认证报错的时候返回错误方法
    //  * @Author: Bernie_fang
    //  * @Since: 2021/8/18 22:49
    //  * @param data:
    //  * @return: com.fang.backgroundapi.common.ServerResponse
    //  **/
    // public static ServerResponse AUTHENTICATION_ERROR(Object data) {
    //     return new ServerResponse(ResponseCode.AUTHENTICATION_ERROR.getCode(), ResponseCode.AUTHENTICATION_ERROR.getDesc(), data);
    // }

    public static ServerResponse error(int code, String message, Object data) {
        return new ServerResponse(code, message, data);
    }

    //未都经过登录认证
    public static ServerResponse createUnLoginError(Object data) {
        return new ServerResponse(ResponseCode.UN_LOGIN.getCode(), ResponseCode.UN_LOGIN.getDesc(), data);
    }

    //未经过授权
    public static ServerResponse createUnAuthorizedError(Object data) {
        return new ServerResponse(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getDesc(), data);
    }

    //登录失败
    public static ServerResponse createLoginFail(Object data) {
        return new ServerResponse(ResponseCode.LOGIN_FAIL.getCode(), ResponseCode.LOGIN_FAIL.getDesc(), data);
    }

    //自定义异常
    public static ServerResponse createExceptionMessage(int status, String msg) {
        return new ServerResponse(status, msg);
    }

}