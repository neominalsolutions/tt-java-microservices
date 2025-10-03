package com.mertalptekin.product_service_demo;

import feign.RequestInterceptor;
import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Zipkin ile birlikte OpenFeign kullanırken, FeignClient isteklerine traceId ve spanId eklemek için interceptor tanımlıyoruz.

@Configuration
public class FeignClientInterceptorConfig {

    // Her bir iç microservis çağırısında istek başlıklarına traceId ve spanId ekliyoruz. Diğer Microservisler tracId aynı kalsın istek distributed bir şekilde devam etsin.

    @Bean
    public RequestInterceptor requestInterceptor(Tracer tracer) {
        return requestTemplate -> {


            System.out.println("Product Service requestInterceptor");

            // b3 header'ını traceId ve spanId ile dolduruyoruz
            // b3 özel bir tracing değeridir.
            var currentSpan = tracer.currentSpan(); // mevcut span'ı alıyoruz
            if(currentSpan != null) {
                requestTemplate.header("b3", currentSpan.context().spanId(), currentSpan.context().traceId());
            }

        };
    }

}
