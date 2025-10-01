package com.mertalptekin.product_service_demo.application.dto;

import java.util.List;

// Request -> OrderId için Productları döndür
public record GetOrderedProductRequest(List<String> ProductIds, String OrderId){}
