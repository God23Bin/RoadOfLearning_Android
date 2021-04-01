package com.bin23.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "接收到自定义广播", Toast.LENGTH_LONG).show();
        String data = intent.getExtras().getString("data");
        Toast.makeText(context, data, Toast.LENGTH_LONG).show();
    }
}
