package com.bbarb75.udemyreporting.API;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Component
public class ApiClient {
    @Value("${udemy.api.id}")
    private String clientID;

    @Value("${udemy.api.secret}")
    private String clientSecret;

    @Value("${udemy.api.accountid}")
    private String accountID;
    private String testApiUrl = "https://comtechnc.udemy.com/api-2.0/organizations/302706/analytics/user-course-activity/";

    public String fetchDataFromApi() {
        try {
            String auth = clientID + ":" + clientSecret;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes()); //handles base64 encoding for basic auth

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(testApiUrl))
                    .header("Authorization", "Basic " + encodedAuth)
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
