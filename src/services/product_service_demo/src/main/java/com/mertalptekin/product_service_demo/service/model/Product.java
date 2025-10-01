package com.mertalptekin.product_service_demo.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id;
    private String name;
    private BigDecimal price;
    private Integer stock;

}
