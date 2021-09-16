package com.fang.backgroundapi.controller;

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

    public Long getLong(String key) {
        String value = request.getParameter(key);
        if (StringUtils.isNotBlank(value)) {
            return Long.parseLong(value.trim());
        }
        return null;
    }

    protected String getToken() {
        return request.getHeader("Authorization");
    }

}
