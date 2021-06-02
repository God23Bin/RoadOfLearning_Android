package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bin23.music.R;
import com.bin23.music.model.UserModel;
import com.bin23.music.utils.UserUtils;
import com.bin23.music.views.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView mIvPhone, mIvPassword, mIvPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        initToolBar(true, "欢迎注册~");

        mIvPhone = fd(R.id.input_phone);
        mIvPassword = fd(R.id.input_password);
        mIvPasswordConfirm = fd(R.id.input_password_confirm);
    }

    public void onRegisterClick(View view) {
        String phone = mIvPhone.getInputStr().trim();
        String password = mIvPassword.getInputStr().trim();
        String passwordC = mIvPasswordConfirm.getInputStr().trim();
        // 进行注册，返回注册结果
        boolean res = UserUtils.register(this, phone, password, passwordC);
        if (!res) {
            return;
        }
        showToast(this, "注册成功~");
        onBackPressed();
    }
}