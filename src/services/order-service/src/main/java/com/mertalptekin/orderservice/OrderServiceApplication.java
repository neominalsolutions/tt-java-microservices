package com.mertalptekin.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.function.Consumer;

@SpringBootApplication
@EnableJpaRepositories
public class OrderServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
