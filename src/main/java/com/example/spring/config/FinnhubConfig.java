package com.example.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinnhubConfig {
    @Value("${finnhub.api.key}")
    private String apiKey;

    @Value("${finnhub.base.url}")
    private String baseUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
