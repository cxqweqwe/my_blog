package com.fang.backgroundapi.exception;

import com.fang.backgroundapi.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bernie_fang
 * @Description: spring boot 异常处理：主要处理shiro注解抛出的异常,统一返回异常结果（json数据）给前端验证
 * @create 2021/8/18 17:14
 **/
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
    /**
     *处理未授权的异常
     */
    @ExceptionHandler(value = AuthorizationException.class)
    public ServerResponse returnErrorMsg(HttpServletRequest request, HttpServletResponse response, AuthorizationException exception){
        return ServerResponse.createUnAuthorizedError("您没有权限");
    }

    /**
     * 处理未登录的异常
     */
    @ExceptionHandler(value = UnauthenticatedException.class)
    public ServerResponse returnUnLoginErrorMsg(HttpServletRequest request, HttpServletResponse response, UnauthenticatedException exception){
        return ServerResponse.createUnLoginError("您还未登录");
    }


    /**
     * 处理运行时异常
     */
    @ExceptionHandler(value = MyException.class)
    public ServerResponse returnRunningException(MyException e){
        log.error("发送错误-------------->", e);
        return ServerResponse.createExceptionMessage(e.getCode(),e.getMsg());
    }

}
