package com.mertalptekin.orderservice.service.entity;
import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(nullable = false)
    private  String name;
}
