package com.fl.bid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
// @EnableEurekaClient
public class FlBidServiceApplication {
	public static void main(String[] args){
		SpringApplication.run(FlBidServiceApplication.class,args);
	}
}
