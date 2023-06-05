package com.fl.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
@EnableScheduling
@SpringBootApplication
@EnableFeignClients
public class FlProjectServiceApplication {
	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(FlProjectServiceApplication.class, args);
	}

}
