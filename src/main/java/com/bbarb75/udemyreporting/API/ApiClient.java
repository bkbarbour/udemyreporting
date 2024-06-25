package com.bbarb75.udemyreporting.API;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ApiClient {
    @Value("${udemy.api.id}")
    private String apiKey;

    @Value("${udemy.api.secret}")
    private String apiSecret;

//    public String fetchDataFromApi(String apiUrl) {
//        try {
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(apiUrl))
//                    .header("X-RapidAPI-Key", apiKey)
//                    .header("X-RapidAPI-Host", apiSecret)
//                    .GET()
//                    .build();
//
//            HttpResponse<String> response = HttpClient.newHttpClient()
//                    .send(request, HttpResponse.BodyHandlers.ofString());
//
//            return response.body();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    //probably switch to unirest for simplicity here
}
