package com.fang.backgroundapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author Bernie_fang
 * @Description:
 * @create 2021/8/22 15:36
 **/
@Configuration
public class SwaggerConfig {


    //配置swagger的bean实例
    @Bean
    public Docket docket(Environment environment) {
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "test");

        //获取项目的环境,通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("fang")
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fang.backgroundapi.controller"))//RequestHandlerSelectors,配置扫描的接口
                .build();
    }

    //配置swagger信息=apiInfo
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("fang", "暂无", "473692484@qq.com");
        return new ApiInfo(
                "个人博客-论坛系统",
                "这个作者有点酷！",
                "v1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }

}
