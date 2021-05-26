package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bin23.music.R;
import com.bin23.music.activities.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}