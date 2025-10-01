package com.mertalptekin.orderservice.controller;


import com.mertalptekin.orderservice.application.CreateOrderHandler;
import com.mertalptekin.orderservice.application.client.ProductClient;
import com.mertalptekin.orderservice.application.dto.CreateOrderRequest;
import com.mertalptekin.orderservice.application.dto.GetOrderedProductRequest;
import com.mertalptekin.orderservice.application.dto.OrderedProductReponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    //@CircuitBreaker(name = "productClient",fallbackMethod = "getOrderedProductsCircuitBraker")
    // @Retry(name = "productClient",fallbackMethod = "getOrderedProductsRetry")
    @RateLimiter(name = "productClient",fallbackMethod = "getOrderedProductsRatelimiter")
    public  ResponseEntity<OrderedProductReponse> getOrderedProducts(@RequestBody GetOrderedProductRequest request){
        System.out.println("Order Servis");
       return productClient.getOrderedProducts(request);
    }

    public ResponseEntity<String> getOrderedProductsRatelimiter(@RequestBody  GetOrderedProductRequest request,Throwable t)  {
        // logger
        System.out.println("rate-limitter");
        // hata dışında eğer veri cachledeye response son güncel cache üzerinden döner.
        return new ResponseEntity<>("429 Status Code", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);

    };

    public ResponseEntity<String> getOrderedProductsRetry(@RequestBody  GetOrderedProductRequest request,Throwable t)  {
        // logger
        System.out.println("retry");
        // hata dışında eğer veri cachledeye response son güncel cache üzerinden döner.
        return ResponseEntity.badRequest().body("Product Client Down");

    };

    public ResponseEntity<String> getOrderedProductsCircuitBraker(@RequestBody  GetOrderedProductRequest request,Throwable t)  {
        // logger
        System.out.println("getOrderedProductsCircuitBrakerFallback");
        // hata dışında eğer veri cachledeye response son güncel cache üzerinden döner.
        return ResponseEntity.badRequest().body("Product Client erişim hatası");

    };

    // herhangi bir hata durumunda productService erişemezsek o zaman response olarak ne göstereceğiz?

    // Bunu test etmek için 1-Eureka Server çalışmalı, 2-OrderService, 3-ProductService
    // Product Service Config ayarlar içinde mecbur Config Serverda açık olmalı. Run

}
