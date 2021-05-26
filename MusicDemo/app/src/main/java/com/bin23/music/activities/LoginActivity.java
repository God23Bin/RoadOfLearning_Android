package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bin23.music.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        initToolBar(false, "欢迎登录~");
    }
}