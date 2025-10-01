package com.mertalptekin.productservice.controller;


// Siparişe düşen ürünleri yönettiğimiz contoller

import com.mertalptekin.productservice.application.dto.GetOrderedProductRequest;
import com.mertalptekin.productservice.application.dto.OrderProductResponse;
import com.mertalptekin.productservice.service.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {


    @PostMapping
    public ResponseEntity<OrderProductResponse> getOrderedProducts(@RequestBody GetOrderedProductRequest request) {

        throw  new RuntimeException("Hatalı istek");

//        System.out.println(request.OrderId());
//        System.out.println(request.ProductIds());
//
//        List<Product> plist = List.of(new Product("1","P-1", BigDecimal.valueOf(10.5),20),new Product("2","P-2"
//        ,BigDecimal.valueOf(30),40));
//
//      return   ResponseEntity.ok(new OrderProductResponse(plist));

    }

}
