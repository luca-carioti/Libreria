package com.uni.libreria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.uni.libreria.entities"})
@EnableJpaRepositories(basePackages = "com.uni.libreria.repositories")
public class LibreriaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibreriaApplication.class, args);
    }
}
