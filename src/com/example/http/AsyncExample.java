package com.example.http;

import java.net.URI;
import java.net.http.*;
import java.time.Duration;
import java.util.concurrent.*;

public class AsyncExample {
    public static void run() throws Exception {
        System.out.println("\n--- Async Examples ---");

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor(); // Java 21 virtual threads
        HttpClient client = HttpClient.newBuilder()
                .executor(executor)
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/delay/2"))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> future = client.sendAsync(
                request, HttpResponse.BodyHandlers.ofString());

        future.thenApply(HttpResponse::body)
                .thenAccept(body -> System.out.println("Async Response: " + body))
                .join();

        executor.shutdown();
    }
}

