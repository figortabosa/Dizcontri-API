package com.exemple.dizcontre.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableTransactionManagement
@EnableWebMvc
@Configuration
public class DizcontreApiApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(DizcontreApiApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/pessoas/**")
		.allowedMethods("*")
		.allowedOrigins("*");
		
		
	}
}
