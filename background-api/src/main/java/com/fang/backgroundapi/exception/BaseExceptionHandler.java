package com.fang.backgroundapi.exception;

import com.fang.backgroundapi.common.ResponseCode;
import com.fang.backgroundapi.common.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Bernie_fang
 * @Description: spring boot 异常处理：主要处理shiro注解抛出的异常,统一返回异常结果（json数据）给前端验证
 * @create 2021/8/18 17:14
 **/
@ControllerAdvice(basePackages = {"com.fang.backgroundapi"})
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


    // /**
    //  *
    //  */
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // @ResponseBody
    // public ServerResponse handleBindException(MethodArgumentNotValidException ex) {
    //     FieldError fieldError = ex.getBindingResult().getFieldError();
    //     log.warn("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
    //     return ServerResponse.error(ResponseCode.DATA_FORMAT_TYPE_ERROR.getCode(),ResponseCode.DATA_FORMAT_TYPE_ERROR.getDesc(),
    //             "错误");
    // }

    /**
     * @Description: 参数校验统一异常处理
     * @Author: Bernie_fang
     * @Since: 2021/8/22 17:16
     * @param ex:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @ExceptionHandler(ConstraintViolationException.class)
    public ServerResponse handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("邮箱检验异常-----------------{}", ex.getMessage());
        int index = ex.getMessage().indexOf(":"); //  ex.getMessage()的格式【方法.参数名字:  错误提示】
        return ServerResponse.error(ResponseCode.DATA_FORMAT_TYPE_ERROR.getCode(), ResponseCode.DATA_FORMAT_TYPE_ERROR.getDesc(),
                ex.getMessage().substring(index + 2));// 只把错误提示给前端，其他的一律不泄漏
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(value = MyException.class)
    public ServerResponse returnRunningMyException(MyException e){
        log.error("发送错误-------------->{}", e.getMsg());
        log.error(this.getTrace(e));
        return ServerResponse.createExceptionMessage(e.getCode(),e.getMsg());
    }

    /**
     * @Description: 处理总异常
     * @Author: Bernie_fang
     * @Since: 2021/8/23 12:17
     * @param e:
     * @return: com.fang.backgroundapi.common.ServerResponse
     **/
    @ExceptionHandler(value = Exception.class)
    public ServerResponse returnRunningException(Exception e){
        log.error("发送错误-------------->{}", e.getMessage());
        log.error(this.getTrace(e));
        return ServerResponse.createExceptionMessage(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    /**
     * @Description: 拿到错误的栈堆信息，变成String
     * @Author: Bernie_fang
     * @Since: 2021/8/22 16:37
     * @param t: 抛出的错误
     * @return: java.lang.String
     **/
    public String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }

}
