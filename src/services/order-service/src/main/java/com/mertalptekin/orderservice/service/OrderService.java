package com.mertalptekin.orderservice.service;

import com.mertalptekin.orderservice.application.event.OrderSummited;
import com.mertalptekin.orderservice.service.entity.Order;
import com.mertalptekin.orderservice.respository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final OrderRepository orderRespository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderService(OrderRepository orderRespository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRespository = orderRespository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public void Save(Order entity){

        // entity.setName(null);
        orderRespository.save(entity);

        // eventi publish ederek süreci OrderSubmittedListener'a devrettik.
        this.applicationEventPublisher.publishEvent(new OrderSummited(entity,"Order Submited"));
    }
}
