package com.xupt.cloud.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by baihuaiyu on 2018/5/1
 */
@EnableEurekaClient
@Configuration
@ImportResource("classpath:spring-config.xml")
@SpringBootApplication
@EnableFeignClients
public class ManagerApiApplication{

    public static void main(String[] args) {
        SpringApplication.run(ManagerApiApplication.class, args);
    }

}