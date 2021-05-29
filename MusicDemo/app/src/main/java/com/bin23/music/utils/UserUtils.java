package com.bin23.music.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.bin23.music.R;
import com.bin23.music.activities.LoginActivity;
import com.blankj.utilcode.util.RegexUtils;

public class UserUtils {

    /**
     * 登录验证
     * @param context
     * @param phone
     * @param password
     * @return
     */
    public static boolean validateLogin(Context context, String phone, String password) {
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "手机号无效", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "密码为空,请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public static void logout(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        // 添加intent标识符，清理task栈，并且新生成task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        // 此时动画错误，（因为动画是基于Activity进栈出栈实现的，现在栈没了，所以需要重新搞）得解决动画错误，重新定义Activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);

    }
}
