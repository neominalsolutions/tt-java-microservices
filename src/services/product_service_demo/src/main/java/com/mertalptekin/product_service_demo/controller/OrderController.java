package com.mertalptekin.product_service_demo.controller;


// Siparişe düşen ürünleri yönettiğimiz contoller


import com.mertalptekin.product_service_demo.application.dto.GetOrderedProductRequest;
import com.mertalptekin.product_service_demo.application.dto.OrderProductResponse;
import com.mertalptekin.product_service_demo.service.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {


    @PostMapping
    public ResponseEntity<OrderProductResponse> getOrderedProducts(@RequestBody GetOrderedProductRequest request) {


        // Not: sadece simile etmek için yaptık
        // 1. Test case Circuit Braker Parameter based Exception
        // 2. Test case sunucu ayakta değil down oldu. Retry pattern anlık servis down durumlarını çözmek için kullanır.

        if(request.OrderId().equals("123")) {
            throw  new IllegalArgumentException("Invalid order id");
        } else {
            System.out.println(request.OrderId());
            System.out.println(request.ProductIds());

            List<Product> plist = List.of(
                    new Product("1","P-1", BigDecimal.valueOf(10.5),20),new Product("2","P-2"
                    ,BigDecimal.valueOf(30),40));

            return   ResponseEntity.ok(new OrderProductResponse(plist));
        }

    }

}
