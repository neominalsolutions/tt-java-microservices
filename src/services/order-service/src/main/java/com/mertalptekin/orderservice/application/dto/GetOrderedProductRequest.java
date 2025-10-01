package com.mertalptekin.orderservice.application.dto;

import java.util.List;

public record GetOrderedProductRequest(List<String> ProductIds,String OrderId) {}

// Not: Hem request hem response nesnesi her iki tarafada yazıldı.
