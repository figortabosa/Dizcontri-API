package com.exemple.dizcontre.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan(basePackages = {"com.exemple.dizcontre.api.model"})
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.exemple.dizcontre.api.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class DizcontreApiApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(DizcontreApiApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/pessoas/**")
		.allowedMethods("*")
		.allowedOrigins("*")
		.allowedHeaders("*")
		.allowCredentials(false);
		
		
	}
}
