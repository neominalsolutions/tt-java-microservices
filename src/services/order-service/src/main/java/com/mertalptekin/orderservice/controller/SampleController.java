package com.mertalptekin.orderservice.controller;

import com.mertalptekin.orderservice.producer.SampleProducer;
import com.mertalptekin.orderservice.service.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/sample")
@AllArgsConstructor
public class SampleController {

    private final SampleProducer sampleProducer;

    @PostMapping
    public ResponseEntity send(@RequestBody String payload) {
        sampleProducer.publish(payload);
        return ResponseEntity.ok("Kafka Mesaj gönderildi");
    };


}
