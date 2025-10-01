package com.mertalptekin.orderservice.application.client;


import com.mertalptekin.orderservice.application.dto.GetOrderedProductRequest;
import com.mertalptekin.orderservice.application.dto.OrderedProductReponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Eureka Server kayıt olduğu ismi çözümlemeye çalışıcaz. Bu ismi kullandık.
@FeignClient(name = "product-service")
public interface ProductClient {

    @PostMapping("/api/v1/orders")
    ResponseEntity<OrderedProductReponse> getOrderedProducts(@RequestBody GetOrderedProductRequest request);
}

// order-service içerisinde dönecek olan veriyi temsil etmek amaçlı yaptık.
// order-service -> product service gidip veri çekmek için yaptık