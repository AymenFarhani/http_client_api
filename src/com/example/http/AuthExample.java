package com.example.http;

import java.net.URI;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.http.*;
import java.time.Duration;
import java.util.Base64;

public class AuthExample {
    public static void run() throws Exception {
        System.out.println("\n--- Auth Examples ---");

        // Basic Auth manually (without Authenticator)
        String auth = Base64.getEncoder().encodeToString("user:pass".getBytes());

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/basic-auth/user/pass"))
                .header("Authorization", "Basic " + auth)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Auth status: " + response.statusCode());
        System.out.println("Auth body: " + response.body());
    }
}
