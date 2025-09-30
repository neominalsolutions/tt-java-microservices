package com.mertalptekin.ttconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // Config server anatasyonu
public class TtConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtConfigServerApplication.class, args);
    }

}
