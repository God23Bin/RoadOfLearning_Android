package com.bin23.servicetest.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        // 必须调用父类的有参构造
        super("MyIntentService");
    }

    /**
     * 处理具体逻辑，不用担心ANR问题
     * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // 打印当前线程的 id
        Log.d("MyIntentService", "Thread id 为" + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 服务运行结束会自动停止，打印日志，看是否停止了
        Log.d("MyIntentService", "onDestroy 执行了");
    }
}
