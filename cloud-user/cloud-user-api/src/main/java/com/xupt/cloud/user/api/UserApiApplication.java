package com.xupt.cloud.user.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by baihuaiyu on 2018/5/2
 */

@EnableEurekaClient
@Configuration
@ImportResource("classpath:spring-config.xml")
@SpringBootApplication
@EnableFeignClients
public class UserApiApplication{

    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }

}
