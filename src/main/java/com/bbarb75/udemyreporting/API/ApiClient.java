package com.bbarb75.udemyreporting.API;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiClient {
    @Value("${udemy.api.id}")
    private String apiKey;

    @Value("${udemy.api.secret}")
    private String apiSecret;
}
