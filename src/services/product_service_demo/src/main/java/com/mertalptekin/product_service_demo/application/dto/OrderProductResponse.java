package com.mertalptekin.product_service_demo.application.dto;


import com.mertalptekin.product_service_demo.service.model.Product;

import java.util.List;
// order servisine göndereceğimiz ürünlerin listesi
public record OrderProductResponse(List<Product> products) {}
