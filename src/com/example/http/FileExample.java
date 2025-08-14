package com.example.http;

import java.net.URI;
import java.net.http.*;
import java.nio.file.*;
public class FileExample {
    public static void run() throws Exception {
        System.out.println("\n--- File Examples ---");

        HttpClient client = HttpClient.newHttpClient();

        // Download file
        HttpRequest download = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/image/png"))
                .build();
        //defined the path
        Path target = Paths.get("downloaded.png");
        //Send the request
        client.send(download, HttpResponse.BodyHandlers.ofFile(target));
        System.out.println("Downloaded file to: " + target.toAbsolutePath());

        // Upload file
        HttpRequest upload = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .header("Content-Type", "application/octet-stream")
                .POST(HttpRequest.BodyPublishers.ofFile(target))
                .build();

        HttpResponse<String> uploadResponse = client.send(upload, HttpResponse.BodyHandlers.ofString());
        System.out.println("Upload status: " + uploadResponse.statusCode());
    }
}
