package com.mertalptekin.orderservice.controller;


import com.mertalptekin.orderservice.application.CreateOrderHandler;
import com.mertalptekin.orderservice.application.dto.CreateOrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final CreateOrderHandler createOrderHandler;

    @PostMapping("submit")
    public ResponseEntity submitOrder(@RequestBody CreateOrderRequest request) {

        createOrderHandler.handle(request);
        return ResponseEntity.ok("İşlem tamam");
    }


}
