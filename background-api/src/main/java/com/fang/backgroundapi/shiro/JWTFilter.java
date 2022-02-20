package com.fang.backgroundapi.shiro;

import com.fang.backgroundapi.utils.JWTUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/17 0:56
 **/
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 判断用户是否登录
     * 检测请求头是否包含token
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        JWTToken jwtToken = new JWTToken(token);
        getSubject(request, response).login(jwtToken);
        Boolean expires = JWTUtil.verifyExpiresInTwoHours(token);
        if (expires){
            // 2小时内过期
            this.RefreshToken(request,response,token);
        }
        this.RefreshToken(request,response,token);
        return true;
    }

    @SneakyThrows
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response)) {
            // try {
            executeLogin(request, response);
            // } catch (Exception e) {
            //     e.printStackTrace();
            // }
        }
        return true;
    }

    public void RefreshToken(ServletRequest request, ServletResponse response,String token){
        String username = JWTUtil.getUsername(token);
        String authorId = JWTUtil.getAuthorId(token);
        String newToken = JWTUtil.signToToken(username, authorId);//重新生成签名token,6个小时
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Authorization",newToken);//设置新的token
        httpResponse.setHeader("Refresh-Token", "true"); //告知前端需要刷新token
        log.info("更新");
    }

}
