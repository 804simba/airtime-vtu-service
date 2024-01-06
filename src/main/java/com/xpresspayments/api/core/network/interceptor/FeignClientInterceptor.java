package com.xpresspayments.api.core.network.interceptor;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class FeignClientInterceptor {
    @Value("${biller.api.key}")
    private String billerApiKey;


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", "Bearer " + billerApiKey);
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Channel", "API");
            requestTemplate.header("PaymentHash", "GENERATED_HMAC");
        };
    }
}
