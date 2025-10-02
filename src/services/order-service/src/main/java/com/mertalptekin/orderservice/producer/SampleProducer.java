package com.mertalptekin.orderservice.producer;


import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SampleProducer {

    private final StreamBridge streamBridge; // kafka event

    public SampleProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public  void  publish(String payload){
        Message<String> message = MessageBuilder.withPayload(payload).build();
        this.streamBridge.send("sample-out-0",message);
    }

}
