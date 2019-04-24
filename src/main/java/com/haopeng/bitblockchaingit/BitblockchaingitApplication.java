package com.haopeng.bitblockchaingit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.haopeng.bitblockchaingit.dao")
//这个注解是让异步起作用
@EnableAsync
public class BitblockchaingitApplication {
    public static void main(String[] args) {
        SpringApplication.run(BitblockchaingitApplication.class, args);
    }
}
