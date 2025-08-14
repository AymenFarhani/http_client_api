package com.example.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.stream.Stream;

public class StreamingExample {
    public static void run() throws Exception {
        System.out.println("\n--- Streaming Examples ---");

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.w3.org/TR/PNG/iso_8859-1.txt"))
                .GET()
                .build();

        HttpResponse<Stream<String>> response = client.send(
                request, HttpResponse.BodyHandlers.ofLines());

        System.out.println("First 5 lines:");
        response.body().limit(5).forEach(System.out::println);
    }
}
