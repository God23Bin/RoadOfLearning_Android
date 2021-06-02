package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bin23.music.R;
import com.bin23.music.utils.SharedPreferencesUtil;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        init();
    }

    private void init() {
        // 用户自动登录，判断是否已经登录，已经登录则进入主页，否则进入登录页面
        final boolean isLogin = SharedPreferencesUtil.isLoginUser(this);

        mTimer = new Timer();
        // schedule(TimerTask task, long delay)
        // 作用
        //   - 等待delay毫秒后执行且仅执行一次task (单次)。
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Log.e("WelcomeActivity", "当前线程为:" + Thread.currentThread().getName());
                // 跳转到MainActivity
//                toMain();
                if (isLogin) {
                    toMain();
                } else {
                    toLogin();
                }
            }
        }, 2 * 1000);
    }

    private void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}