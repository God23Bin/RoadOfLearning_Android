package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bin23.music.R;

public class UpdatePwActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pw);

        initView();
    }

    private void initView() {
        initToolBar(true, "修改密码");
    }

    public void onUpdatePwConfirmBtnClick(View view) {

    }
}