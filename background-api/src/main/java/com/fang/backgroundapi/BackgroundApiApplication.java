package com.fang.backgroundapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com/fang/backgroundapi/mapper") //mapper扫描文件夹
@EnableTransactionManagement
@SpringBootApplication
public class BackgroundApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackgroundApiApplication.class, args);
    }

}
