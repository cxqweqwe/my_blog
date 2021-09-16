package com.fang.backgroundapi.config;

import com.fang.backgroundapi.exception.ExceptionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/9/16 16:51
 **/
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean exceptionFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ExceptionFilter());
        registration.setName("ExceptionFilter");
        //此处尽量小，要比其他Filter靠前
        registration.setOrder(-1);
        return registration;
    }

}
