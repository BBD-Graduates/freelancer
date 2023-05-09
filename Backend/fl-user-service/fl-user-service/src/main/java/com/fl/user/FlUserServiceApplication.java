package com.fl.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients
public class FlUserServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FlUserServiceApplication.class, args);
	}

}
