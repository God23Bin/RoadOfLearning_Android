package com.bin23.servicetest.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


import androidx.core.app.NotificationCompat;

import com.bin23.servicetest.MainActivity;
import com.bin23.servicetest.R;

public class MyService extends Service {

    // DownloadBinder 继承 Binder
    // 提供两个方法
    public class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("我的服务-MyService", "startDownload()执行了");
        }

        public int getProgress() {
            Log.d("我的服务-MyService", "getProgress()执行了");
            return 0;
        }
    }

    // 创建 DownloadBinder 实例
    private DownloadBinder mBinder = new DownloadBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // 返回该实例
        return mBinder;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onCreate() {
        super.onCreate();
        Log.d("MyService-我的服务", "onCreate() 执行了");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this, "default")
                .setContentTitle("这是Content的标题")
                .setContentText("这是Content内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build()
                ;
        startForeground(1, notification);
    }

    /**
     * 每次服务启动的时候调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService-我的服务", "onStartCommand() 执行了");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService-我的服务", "onDestroy() 执行了");
    }
}
