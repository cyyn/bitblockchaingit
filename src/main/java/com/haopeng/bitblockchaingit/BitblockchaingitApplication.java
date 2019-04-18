package com.haopeng.bitblockchaingit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.haopeng.bitblockchaingit.dao")
public class BitblockchaingitApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitblockchaingitApplication.class, args);
    }

}
