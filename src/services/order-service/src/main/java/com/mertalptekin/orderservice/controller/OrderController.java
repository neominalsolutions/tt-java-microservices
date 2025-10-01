package com.mertalptekin.orderservice.controller;


import com.mertalptekin.orderservice.application.CreateOrderHandler;
import com.mertalptekin.orderservice.application.client.ProductClient;
import com.mertalptekin.orderservice.application.dto.CreateOrderRequest;
import com.mertalptekin.orderservice.application.dto.GetOrderedProductRequest;
import com.mertalptekin.orderservice.application.dto.OrderedProductReponse;
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
    private final ProductClient productClient;

    @PostMapping("submit")
    public ResponseEntity submitOrder(@RequestBody CreateOrderRequest request) {

        createOrderHandler.handle(request);
        return ResponseEntity.ok("İşlem tamam");
    }

    // api/v1/orders -> POST isteği
    @PostMapping
    public  ResponseEntity<OrderedProductReponse> getOrderedProducts(@RequestBody GetOrderedProductRequest request){
       return productClient.getOrderedProducts(request);
    }

    // Bunu test etmek için 1-Eureka Server çalışmalı, 2-OrderService, 3-ProductService
    // Product Service Config ayarlar içinde mecbur Config Serverda açık olmalı. Run

}
