package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bin23.music.R;
import com.bin23.music.utils.UserUtils;
import com.bin23.music.views.InputView;

public class LoginActivity extends BaseActivity {

    private InputView mIvPhone, mIvPassword;

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

        mIvPhone = fd(R.id.input_phone);
        mIvPassword = fd(R.id.input_password);
    }

    /**
     * 登录
     */
    public void onCommitClick(View view) {
        String phone = mIvPhone.getInputStr();
        String password = mIvPassword.getInputStr();
        if (!UserUtils.login(this, phone, password)) {
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 立即注册，跳转注册页面
     */
    public void onRegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * 忘记密码
     */
    public void onForgetPwClick(View view) {

    }
}