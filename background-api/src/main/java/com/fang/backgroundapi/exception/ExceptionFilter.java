package com.fang.backgroundapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/9/16 16:49
 **/

@Slf4j
@Component
public class ExceptionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (UnknownAccountException e) {
            //将异常分发到/error/exthrow控制器

        }catch(Exception e){
            Throwable cause = e.getCause();
            if (cause instanceof UnknownAccountException){
                // 如果是 UnknownAccountException 错，直接调用到这个控制器
                request.getRequestDispatcher("/error/throw/uka").forward(request, response);
            }

            // 异常捕获，发送到error controller
            request.setAttribute("filter.error", e);
            //将异常分发到/error/exthrow控制器
            request.getRequestDispatcher("/error/throw").forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }

}
