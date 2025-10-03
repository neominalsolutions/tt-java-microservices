package com.mertalptekin.product_service_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductServiceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceDemoApplication.class, args);
    }

}
