package com.mertalptekin.orderservice.application;


import com.mertalptekin.orderservice.application.dto.CreateOrderRequest;
import com.mertalptekin.orderservice.service.entity.Order;
import com.mertalptekin.orderservice.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderHandler {

    private final OrderService orderService;

    public CreateOrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }


    public  void  handle(CreateOrderRequest request){
        // validation
        // mapping
        Order entity = new Order();
        // entity.setId(1); // Auto increment
        entity.setName(request.name());
        this.orderService.Save(entity);

        // eventpublisher.send(event);

    }
}
