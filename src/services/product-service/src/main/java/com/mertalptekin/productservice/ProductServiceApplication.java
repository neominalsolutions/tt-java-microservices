package com.mertalptekin.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}


// Not: cleint uygulama farklı portalardan servis versin ki eureak server load balancing yapabilsin
// Not: mvn spring-boot:run -D"spring-boot.run.arguments=--server.port=8088" 8890
// Not: Opefeign ile test edelim.
// Not: http://localhost:8761/eureka clientların kendilerini register ettiği endpoint

