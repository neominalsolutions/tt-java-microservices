package com.mertalptekin.orderservice.producer;


import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SampleProducer {

    private final StreamBridge streamBridge; // kafka event

    public SampleProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public  void  publish(String payload){

        String key = payload.contains("fail") ? "1":"2";

        // payload göre key oluşturma
        Message<String> message = MessageBuilder.withPayload(payload).setHeader("correlationId", key).build();
        this.streamBridge.send("sample-out-0",message);
    }

}
