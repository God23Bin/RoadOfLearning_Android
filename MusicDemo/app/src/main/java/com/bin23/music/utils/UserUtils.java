package com.bin23.music.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

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
}
