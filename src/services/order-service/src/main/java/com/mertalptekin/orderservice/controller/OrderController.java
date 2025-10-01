package com.mertalptekin.orderservice.controller;


import com.mertalptekin.orderservice.application.CreateOrderHandler;
import com.mertalptekin.orderservice.application.client.ProductClient;
import com.mertalptekin.orderservice.application.dto.CreateOrderRequest;
import com.mertalptekin.orderservice.application.dto.GetOrderedProductRequest;
import com.mertalptekin.orderservice.application.dto.OrderedProductReponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    @CircuitBreaker(name = "productClient",fallbackMethod = "getOrderedProductsCircuitBraker")
    public  ResponseEntity<OrderedProductReponse> getOrderedProducts(@RequestBody GetOrderedProductRequest request){
        System.out.println("Order Servis");
       return productClient.getOrderedProducts(request);
    }

    public ResponseEntity<OrderedProductReponse> getOrderedProductsCircuitBraker(Exception ex)  {
        // logger
        System.out.println("getOrderedProductsCircuitBrakerFallback");
        // hata dışında eğer veri cachledeye response son güncel cache üzerinden döner.
        return ResponseEntity.ok(new OrderedProductReponse(List.of()));

    };

    // herhangi bir hata durumunda productService erişemezsek o zaman response olarak ne göstereceğiz?

    // Bunu test etmek için 1-Eureka Server çalışmalı, 2-OrderService, 3-ProductService
    // Product Service Config ayarlar içinde mecbur Config Serverda açık olmalı. Run

}
