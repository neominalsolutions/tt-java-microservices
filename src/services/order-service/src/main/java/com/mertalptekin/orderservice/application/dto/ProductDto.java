package com.mertalptekin.orderservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String id;
    private String name;
    private BigDecimal price;
    private Integer stock;

}
