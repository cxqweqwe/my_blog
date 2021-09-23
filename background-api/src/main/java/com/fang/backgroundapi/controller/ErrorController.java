package com.fang.backgroundapi.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Bernie_fang
 * @Description: 这个是处理异常的请求。Filter跑出来的错误会转发到这里
 * @create 2021/9/16 16:53
 **/
@RestController
@RequestMapping("/error")
@Api(tags = "请勿请求此接口集的任何请求")
public class ErrorController {

    @RequestMapping("/throw")
    public void rethrow(HttpServletRequest request) throws Exception {
        request.getAttribute("filter.error");
        throw ((Exception) request.getAttribute("filter.error"));
    }

    /**
     * @Description: Filter拦截器严重shiro时出现UnknownAccountException会重定向此接口
     * @Author: Bernie_fang
     * @Since: 2021/9/16 17:22
     * @param request:
     * @return: void
     **/
    @RequestMapping("/throw/uka")
    public void throwUnknownAccountException(HttpServletRequest request) throws Exception {
        throw new UnknownAccountException();
    }

}
