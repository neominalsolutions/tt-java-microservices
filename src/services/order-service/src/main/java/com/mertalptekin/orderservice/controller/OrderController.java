package com.mertalptekin.orderservice.controller;


import com.mertalptekin.orderservice.application.CreateOrderHandler;
import com.mertalptekin.orderservice.application.client.ProductClient;
import com.mertalptekin.orderservice.application.dto.CreateOrderRequest;
import com.mertalptekin.orderservice.application.dto.GetOrderedProductRequest;
import com.mertalptekin.orderservice.application.dto.OrderedProductReponse;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final CreateOrderHandler createOrderHandler;
    private final ProductClient productClient;

    @GetMapping
    private String Index(){
        return  "Order Service";
    }

    @PostMapping("submit")
    public ResponseEntity submitOrder(@RequestBody CreateOrderRequest request) {

        createOrderHandler.handle(request);
        return ResponseEntity.ok("İşlem tamam");
    }

    // api/v1/orders -> POST isteği
    @PostMapping
    //@CircuitBreaker(name = "productClient",fallbackMethod = "circuitBraker")
    @Retry(name = "productClient",fallbackMethod = "retry")
    //@RateLimiter(name = "productClient",fallbackMethod = "ratelimiter")
    public  ResponseEntity<OrderedProductReponse> getOrderedProducts(@RequestBody GetOrderedProductRequest request){
        System.out.println("Order Servis");
       return productClient.getOrderedProducts(request);
    }

    public ResponseEntity<String> ratelimiter(@RequestBody  GetOrderedProductRequest request,Throwable t)  {
        // logger
        System.out.println("rate-limitter");
        // hata dışında eğer veri cachledeye response son güncel cache üzerinden döner.
        return new ResponseEntity<>("429 Status Code", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);

    };

    public ResponseEntity<String> retry(@RequestBody  GetOrderedProductRequest request,Throwable t)  {
        // logger
        System.out.println("retry");
        // hata dışında eğer veri cachledeye response son güncel cache üzerinden döner.
        return ResponseEntity.badRequest().body("Product Client Down");

    };

    public ResponseEntity<String> circuitBraker(@RequestBody  GetOrderedProductRequest request,Throwable t)  {
        // logger
        System.out.println("circuitBraker");
        // hata dışında eğer veri cachledeye response son güncel cache üzerinden döner.
        return ResponseEntity.badRequest().body("Product Client erişim hatası");

    };

    // herhangi bir hata durumunda productService erişemezsek o zaman response olarak ne göstereceğiz?
    // Bunu test etmek için 1-Eureka Server çalışmalı, 2-OrderService, 3-ProductService
    // Product Service Config ayarlar içinde mecbur Config Serverda açık olmalı. Run

}
