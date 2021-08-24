package com.example.atos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.example.atos.repositories" })
@ComponentScan(basePackages = { "com.example" })
@EntityScan(basePackages = { "com.example.atos.models" })
@SpringBootApplication
public class AtosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtosApplication.class, args);
	}
}
