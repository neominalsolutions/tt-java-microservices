package com.mertalptekin.productservice.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class HomeController {

    @Value("${serviceName}")
    private String serviceName;





    @GetMapping
    public String info() {
        return " name: " + serviceName;
    }


}
