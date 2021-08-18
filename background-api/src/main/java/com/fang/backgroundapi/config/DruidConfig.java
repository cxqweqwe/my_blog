package com.fang.backgroundapi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 配置阿里巴巴的数据源以及Druid后台sql监控
 * @Author: Bernie_fang
 * @Since: 2021/8/15 23:50
 **/
@Configuration
public class DruidConfig {


    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(name = "druidDataSource")
    public DataSource getDruid(){
        return new DruidDataSource();
    }

    //配置管理后台的servlet,数据源的servlet为：StatViewServlet()
    @Bean
    public ServletRegistrationBean setStatViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet());
        bean.setUrlMappings(Arrays.asList(new String[]{"/druid/*"}));
        Map<String, String> initParams = new HashMap<>();
        //登录后台的用户名
        initParams.put("loginUsername", "fangblog");
        //后台的登录密码
        initParams.put("loginPassword", "fangblog");
        //允许的访问地址，空字符串表示允许所有
        initParams.put("allow", "");
        //可以设置拒绝某些ip地址的访问
        //initParams.put("deny","");
        bean.setInitParameters(initParams);
        return bean;
    }

    //配置web监控的拦截器Filter,druid提供的filter为：WebStatFilter
    @Bean
    public FilterRegistrationBean setFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //拦截所有的地址
        bean.setUrlPatterns(Arrays.asList(new String[] {"/*"}));
        //有些地址不能拦截的，所以要配置exclusion排除掉
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusion","*.js,*.css,*.jsp,*.png,/druid/*");
        bean.setInitParameters(initParams);
        return bean;
    }

    @Bean(name = "detTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("druidDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
