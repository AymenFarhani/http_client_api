package com.example.http;


public class Demo {

    public static void main(String[] args) throws Exception {
        SyncExample.run();
        AsyncExample.run();
        StreamingExample.run();
        FileExample.run();
        AuthExample.run();
        WebSocketExample.run();
    }
}
