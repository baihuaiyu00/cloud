package com.xupt.cloud.file;

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
public class FileApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApiApplication.class, args);
    }

}
