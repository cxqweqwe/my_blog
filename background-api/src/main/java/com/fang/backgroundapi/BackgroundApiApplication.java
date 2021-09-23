package com.fang.backgroundapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@MapperScan("com/fang/backgroundapi/mapper") //mapper扫描文件夹
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
public class BackgroundApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackgroundApiApplication.class, args);
    }

}
