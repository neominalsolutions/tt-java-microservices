package com.mertalptekin.orderservice.application.event;


// Not: ApplicationEvent olarak In Memory kullanılan eventleri class yapıyoruz ve dili geçmiş zaman kipinde isimlendiriyoruz
// eventler veri taşımamızı sağlayan yapılar. Bu yapılara tanımlanan argüman değerleri üzerinden eventhandlerlara veri geçiyoruz.

import com.mertalptekin.orderservice.service.entity.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderSummited extends ApplicationEvent {

    private final String message;

    public OrderSummited(Order source,String message) {
        super(source);
        this.message = message;
    }
}
