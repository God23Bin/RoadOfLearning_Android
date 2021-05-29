package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bin23.music.R;

public class MeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    private void initView() {
        initToolBar(true, "我的资料");
    }


}