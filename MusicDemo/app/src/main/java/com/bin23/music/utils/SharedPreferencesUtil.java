package com.bin23.music.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bin23.music.constants.SPConstants;
import com.bin23.music.helps.UserHelper;

public class SharedPreferencesUtil {

    /**
     * 当用户登录时，使用 SharedPreferences 保存登录用户的用户标记
     * @param context
     * @param phone
     * @return
     */
    public static boolean saveUser(Context context, String phone){
        SharedPreferences sp=context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sp.edit();
        editor.putString(SPConstants.SP_KEY_PHONE,phone);
        boolean result=editor.commit();
        return  result;
    }

    /**
     * 判断当前是否存在已登录用户
     * @param context
     * @return
     */
    public static boolean isLoginUser(Context context){
        boolean result = false;

        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);

        String phone = sp.getString(SPConstants.SP_KEY_PHONE, "");
        // 从 SP 中取出来不为空，说明存在已登录用户
        if (!TextUtils.isEmpty(phone)) {
            result = true;
            UserHelper.getInstance().setPhone(phone);
        }
        return result;
    }

    /**
     * 移除标记
     * @param context
     * @return
     */
    public static boolean removeUser(Context context){
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.remove(SPConstants.SP_KEY_PHONE);
        boolean result = editor.commit();
        return result;
    }
}
