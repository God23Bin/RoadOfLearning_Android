package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bin23.music.R;
import com.bin23.music.utils.UserUtils;
import com.bin23.music.views.InputView;

public class UpdatePwActivity extends BaseActivity {

    private InputView oldPwd, newPwd, newPwdConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pw);

        initView();
    }

    private void initView() {
        initToolBar(true, "修改密码");
        oldPwd = fd(R.id.input_old_password);
        newPwd = fd(R.id.input_new_password);
        newPwdConfirm = fd(R.id.input_new_confirm_password);
    }

    public void onUpdatePwConfirmBtnClick(View view) {
        String oldPassword = oldPwd.getInputStr();
        String password = newPwd.getInputStr();
        String passwordConfirm = newPwdConfirm.getInputStr();

        boolean result = UserUtils.updatePassword(this, oldPassword, password, passwordConfirm);
        if(!result){
            return ;
        }
        showToast(this, "成功修改密码~");
        onBackPressed();
    }
}