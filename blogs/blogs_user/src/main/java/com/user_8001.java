package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@EnableEurekaClient
@ImportResource(locations = {"classpath:druid-beans.xml"})
public class user_8001 {
    public static void main(String[] args) {
        SpringApplication.run(user_8001.class, args);
    }
}
