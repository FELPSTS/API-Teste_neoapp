package com.example.ApiTeste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.ApiTeste.model")
@EnableJpaRepositories(basePackages = "com.example.ApiTeste.repository")
public class ApiTesteApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiTesteApplication.class, args);
    }
}