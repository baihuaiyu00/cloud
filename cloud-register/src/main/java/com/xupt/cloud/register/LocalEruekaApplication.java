package com.xupt.cloud.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class LocalEruekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalEruekaApplication.class, args);
	}
}
