package com.fang.backgroundapi.service.impl;

import com.fang.backgroundapi.common.CommonInfo;
import com.fang.backgroundapi.common.ResponseCode;
import com.fang.backgroundapi.common.ServerResponse;
import com.fang.backgroundapi.exception.MyException;
import com.fang.backgroundapi.pojo.DO.SysUsers;
import com.fang.backgroundapi.shiro.JWTToken;
import com.fang.backgroundapi.utils.JWTUtil;
import com.fang.backgroundapi.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bernie_fang
 * @Description: 这是一个通用服务类，没有接口
 * @create 2021/8/18 16:47
 **/
@Service
public class CommonServiceImpl {

    @Autowired
    private SysUsersServiceImpl sysUsersService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @Description: 用户根据用户名和密码登录
     * @Author: Bernie_fang
     * @Since: 2021/8/18 17:30
     * @param username:
     * @param password:
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

        // String sha1Password = PasswordUtil.sha1Encode(password); //暂时关闭，后面记得开启
        if (!sysUser.getPassword().equals(password)) {
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
        redisUtils.set(CommonInfo.SYS_USER+ username,sysUser,CommonInfo.EXPIRATION_TIME_SECOND);
        // TODO： 设置请求头的token
        response.setHeader("Authorization", token); //放到信息头部
        return ServerResponse.success();
    }

}
