package com.mertalptekin.orderservice;

import com.netflix.discovery.EurekaClient;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class EurekaUnregister {
    private EurekaClient eurekaClient;

    @PreDestroy
    public void init() {
        System.out.println("Euraka Client Temizle");
        eurekaClient.shutdown();
    }

}
