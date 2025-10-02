package com.mertalptekin.orderservice;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.function.Consumer;

@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    // Stream Cloud Function -> binding için bunu teikleyecek bir functionName definition yapıcaz.
    // 2. adım
    @Bean
    public Consumer<Message<String>> sample() {
        return message -> {

            // Consumer herhangi bir hata durumunda dql otmatik dolar.
            if(message.getPayload().contains("fail")){
                throw new RuntimeException("Consumer Error");
            }

            System.out.println("message:" + message.getPayload());
        };
    }

    @Bean
    public Consumer<Message<String>> sample01() {
        return message -> {

            System.out.println("message 01:" + message.getPayload());
        };
    }

    // Not: genelde @Scheduled birlikte cron bazlı kullanırız.

    // @Bean
    public Consumer<Message<String>> sample_error() {
        return message -> {
            System.out.println("Dql Error Message:" + message.getPayload());
        };
    }


}
