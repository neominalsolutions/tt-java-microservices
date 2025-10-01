package com.mertalptekin.orderservice.repository;
import com.mertalptekin.orderservice.service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order,Long> { }
