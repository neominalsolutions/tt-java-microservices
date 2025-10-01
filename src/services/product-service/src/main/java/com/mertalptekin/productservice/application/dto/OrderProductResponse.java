package com.mertalptekin.productservice.application.dto;

import com.mertalptekin.productservice.service.model.Product;

import java.util.List;
// order servisine göndereceğimiz ürünlerin listesi
public record OrderProductResponse(List<Product> products) {}
