package com.example.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

// (Basic GET/POST, HTTP/2) Example
public class SyncExample {

    public static void run() throws IOException, InterruptedException {
        System.out.println("------Synchronous Example------");
         //Build the HTTPClient using Builder
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        // Create a Simple Get Request
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .GET()
                .build();

        //Send the request and Retrieve the response
        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("GET status: " + response.statusCode());
        System.out.println("GET body: " + response.body());

        // POST JSON
        String json = """
            { "name": "Alice", "role": "developer" }
            """;

        //Create a simple POST request
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        //Send the request and get the response
        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST Status: " + postResponse.statusCode());
        System.out.println("POST Body: " + postResponse.body());
    }
}
