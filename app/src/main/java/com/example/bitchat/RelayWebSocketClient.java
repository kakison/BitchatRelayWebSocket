package com.example.bitchat;

import android.util.Log;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class RelayWebSocketClient {

    private WebSocketClient wsClient;
    private BitchatService service;

    public RelayWebSocketClient(BitchatService service) {
        this.service = service;
        try {
            wsClient = new WebSocketClient(new URI("ws://192.168.1.100:8080")) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.d("WSClient", "Connected to WebSocket server");
                }

                @Override
                public void onMessage(String message) {
                    Log.d("WSClient", "Received: " + message);
                    service.forwardToMesh(message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("WSClient", "Closed: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    Log.e("WSClient", "Error: " + ex.getMessage());
                }
            };
            wsClient.connect();
        } catch (Exception e) {
            Log.e("WSClient", "WebSocket init error: " + e.getMessage());
        }
    }

    public void sendMessage(String msg) {
        if (wsClient != null && wsClient.isOpen()) {
            wsClient.send(msg);
        }
    }
}
