package com.mertalptekin.productservice.application.dto;

import java.util.List;

// Request -> OrderId için Productları döndür
public record GetOrderedProductRequest(List<String> ProductIds, String OrderId){}
