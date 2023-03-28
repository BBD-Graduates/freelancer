package com.fl.skill.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SpringFoxConfig {

    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI().info(new Info().title("FL-Skill-Service").version("1.0.0").description("Spring doc"));
    }
}