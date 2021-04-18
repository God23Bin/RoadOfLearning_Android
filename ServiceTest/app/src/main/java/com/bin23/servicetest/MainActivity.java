package com.bin23.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bin23.servicetest.service.MyIntentService;
import com.bin23.servicetest.service.MyService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 引入Binder
    public MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        // Activity与Service绑定的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 向下转型 获取 DownloadBinder实例
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
        // Activity与Service解绑的时候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button)findViewById(R.id.start_service);
        Button stopBtn = (Button)findViewById(R.id.stop_service);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

        Button bindBtn = (Button)findViewById(R.id.bind_service);
        Button unbindBtn = (Button)findViewById(R.id.unbind_service);
        bindBtn.setOnClickListener(this);
        unbindBtn.setOnClickListener(this);

        Button startIntentServiceBtn = (Button)findViewById(R.id.start_intent_service);
        startIntentServiceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);// 启动服务
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);// 停止服务
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                // connection是ServiceConnection实例
                // BIND_AUTO_CREATE 表示活动和服务进行绑定后自动创建服务，会使MyService中的onCreate()方法得到执行
                bindService(bindIntent, connection, BIND_AUTO_CREATE); // 将Activity与Service绑定
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            case R.id.start_intent_service:
                Log.d("MainActivity", "Thread id 是" + Thread.currentThread().getId());
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}