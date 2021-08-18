package com.fang.backgroundapi.shiro;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * @author Bernie_fang
 * @Description: 把Shiro默认的Token改为JWT
 * @create 2021/8/17 0:56
 **/
public class JWTToken implements AuthenticationToken {
    //以下相当于重写shiro框架自带的token
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
