package com.fang.backgroundapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@MapperScan("com/fang/backgroundapi/mapper") //mapper扫描文件夹
@EnableTransactionManagement(proxyTargetClass = true)
@EnableScheduling       // 开启定时任务功能
@SpringBootApplication
public class BackgroundApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackgroundApiApplication.class, args);
    }

}
