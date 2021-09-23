package com.fang.backgroundapi.controller;

import com.fang.backgroundapi.common.ResponseCode;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.utils.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/9/16 11:07
 **/
@Controller
public class BaseController {


    @Autowired
    protected HttpServletRequest request;

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    public HttpSession getSession() {
        HttpSession session = getRequest().getSession();
        return session;
    }

    protected String getToken() throws MyException {
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)){
            throw new MyException(ResponseCode.UN_LOGIN.getDesc(), ResponseCode.UN_LOGIN.getCode());
        }
        return token;
    }

    protected String getAuthorId() throws MyException {
        String token = getToken();
        String authorId = JWTUtil.getAuthorId(token);
        return authorId;
    }

}
