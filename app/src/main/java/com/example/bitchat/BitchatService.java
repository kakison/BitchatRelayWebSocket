package com.example.bitchat;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

public class BitchatService extends Service {

    private RelayWebSocketClient relayClient;

    @Override
    public void onCreate() {
        super.onCreate();
        initMesh();
        relayClient = new RelayWebSocketClient(this);
    }

    private void initMesh() {
        // TODO: Masukkan kode Bitchat asli untuk BLE mesh
        Log.d("BitchatService", "Mesh initialized");
    }

    public void forwardToMesh(String message) {
        // TODO: Forward pesan ke BLE mesh
        Log.d("BitchatService", "Forward to mesh: " + message);
    }

    public void forwardToServer(String message) {
        if (relayClient != null) {
            relayClient.sendMessage(message);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
