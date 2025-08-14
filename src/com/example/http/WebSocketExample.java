package com.example.http;

import java.net.URI;
import java.net.http.*;
import java.net.http.WebSocket.*;
import java.util.concurrent.*;

public class WebSocketExample {
    public static void run() throws Exception {
        System.out.println("\n--- WebSocket Example ---");

        HttpClient client = HttpClient.newHttpClient();

        CompletableFuture<WebSocket> wsFuture = client.newWebSocketBuilder()
                .buildAsync(URI.create("wss://echo.websocket.events"), new Listener() {
                    @Override
                    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                        System.out.println("Received: " + data);
                        return Listener.super.onText(webSocket, data, last);
                    }
                });

        WebSocket ws = wsFuture.join();
        ws.sendText("Hello WebSocket!", true);
        Thread.sleep(1000); // wait to receive
        ws.sendClose(WebSocket.NORMAL_CLOSURE, "Bye").join();
    }
}

