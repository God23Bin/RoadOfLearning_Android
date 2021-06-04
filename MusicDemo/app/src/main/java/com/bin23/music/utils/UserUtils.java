package com.bin23.music.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bin23.music.R;
import com.bin23.music.activities.LoginActivity;
import com.bin23.music.helps.RealmHelper;
import com.bin23.music.helps.UserHelper;
import com.bin23.music.model.UserModel;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;

public class UserUtils {

    private static final String TAG = "UserUtils";

    /**
     * 一系列封装
     */
    private static final String PHONE_IS_EMPTY = "手机号为空，请输入手机号";
    private static final String PHONE_INVALID = "请输入正确的手机号";
    private static final String PASSWORD_IS_EMPTY = "密码为空，请输入密码";
    private static final String PASSWORD_NOT_EQUAL = "请确认两次密码输入是否一致";
    private static final String PHONE_HAS_EXITS = "该手机号已经注册";
    private static final String PHONE_NO_EXITS = "该手机号未注册";
    private static final String LOGIN_FAIL = "登陆失败：手机号或密码不正确";
    private static final String SAVE_USER_TO_SP_FAIL = "登陆失败：应用发生错误，请稍后重试";
    private static final String REMOVE_USER_FROM_SP_FAIL = "退出失败：应用发生错误，请稍后重试";

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 登录验证的封装
     * 使用RegexUtils进行登录输入数据的合法性验证
     * 还使用UserUtils本身提供的验证手机号是否已经注册的方法来判断
     * 1.验证手机号是否为空
     * 2.验证手机号是否合法
     * 3.验证密码是否为空
     *
     * 4.验证手机号是否已经注册
     * 5.验证用户输入的手机号和密码是否匹配
     * @param context
     * @param phone
     * @param password
     * @return 合法返回true，不合法返回false
     */
    public static boolean validationOfLogin(Context context, String phone, String password) {
        if (TextUtils.isEmpty(phone)) {
            showToast(context, PHONE_IS_EMPTY);
            return false;
        }
        if (!RegexUtils.isMobileExact(phone)) {
            showToast(context, PHONE_INVALID);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            showToast(context, PASSWORD_IS_EMPTY);
            return false;
        }
        if (!UserUtils.userExistFromPhone(phone)) {
            showToast(context, PHONE_NO_EXITS);
            return false;
        }
        return true;
    }

    /**
     * 注册验证的封装
     * 使用RegexUtils进行注册输入数据的合法性验证
     * 还使用UserUtils本身提供的验证手机号是否已经注册的方法来判断
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     * @return
     */
    public static boolean validationOfRegister(Context context, String phone, String password, String passwordConfirm) {
        if(!RegexUtils.isMobileExact(phone)){
            showToast(context, PHONE_INVALID);
            return false;
        }
        if(StringUtils.isEmpty(password)){
            showToast(context, PASSWORD_IS_EMPTY);
            return false;
        }
        if(!password.equals(passwordConfirm)){
            showToast(context, PASSWORD_NOT_EQUAL);
            return false;
        }
        // 判断用户当前输入的手机号是否已经被注册
        if (UserUtils.userExistFromPhone(phone)) {
            showToast(context, PHONE_HAS_EXITS);
            return false;
        }
        return true;
    }

    /**
     * 用户登录方法
     * @param context
     * @param phone
     * @param password
     * @return
     */
    public static boolean login(Context context, String phone, String password) {
        // 判断登录输入数据是否合法，如果不合法返回false
        if (!validationOfLogin(context, phone, password)) {
            return false;
        }

        RealmHelper realmHelper = new RealmHelper();
        boolean res = realmHelper.validateUser(phone, EncryptUtils.encryptMD5ToString(password));

        if (!res) {
            showToast(context, LOGIN_FAIL);
            return false;
        }
        // 保存用户标记，为实现自动登录做准备
        boolean isSave = SharedPreferencesUtil.saveUser(context, phone);
        if (!isSave) {
            showToast(context, SAVE_USER_TO_SP_FAIL);
            return false;
        }
        // 在全局单例类中保存用户登录标记
        UserHelper.getInstance().setPhone(phone);
        UserHelper.getInstance().setUsername("默认的昵称哦");

        // 保存音乐源数据
        realmHelper.setMusicSource(context);

        realmHelper.close();

        return true;
    }

    public static void logout(Context context) {
        boolean isRemove = SharedPreferencesUtil.removeUser(context);
        if (!isRemove) {
            showToast(context, REMOVE_USER_FROM_SP_FAIL);
            return;
        }

       // 删除数据源
       RealmHelper realmHelper = new RealmHelper();
       realmHelper.removeMusicSource();
       
       realmHelper.close();

        Intent intent = new Intent(context, LoginActivity.class);
        // 添加intent标识符，清理task栈，并且新生成task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        // 此时动画错误，（因为动画是基于Activity进栈出栈实现的，现在栈没了，所以需要重新搞）得解决动画错误，重新定义Activity跳转动画
//        ((Activity)context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);

    }

    /**
     * 用户注册方法
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     * @return
     */
    public static boolean register(Context context, String phone, String password, String passwordConfirm){
        // 判断注册输入数据是否合法，如果不合法返回false
        if (!validationOfRegister(context, phone, password, passwordConfirm)) {
            return false;
        }

        UserModel user = new UserModel();
        user.setPhone(phone);
        user.setPassword(EncryptUtils.encryptMD5ToString(password));
//        user.setUsername("这是默认昵称哦");

        saveUser(user);
        return true;
    }

    /**
     * 存储用户数据到数据库
     * @param userModel
     */
    public static void saveUser(UserModel userModel){
        RealmHelper realmHelp = new RealmHelper();
        realmHelp.saveUser(userModel);
        realmHelp.close();
    }

    /**
     * 根据手机号判断用户是否存在
     * @param phone
     */
    public static boolean userExistFromPhone(String phone) {
        boolean res = false;
        // 获取数据库中所有用户数据
        RealmHelper realmHelper = new RealmHelper();
        List<UserModel> allUser = realmHelper.getAllUser();
        // 循环遍历匹配是否已存在传入的 phone
        for (UserModel userModel : allUser) {
            if (userModel.getPhone().equals(phone)) {
                res = true;
                break;
            }
        }
        realmHelper.close();
        return res;
    }

    /**
     * 验证是否存在已登录用户
     * @param context
     * @return
     */
    public static boolean validateUserLogin(Context context){
        return SharedPreferencesUtil.isLoginUser(context);
    }

    /**
     * 修改密码
     *  1.数据验证
     *      1.原密码是否输入
     *      2.新密码是否输入且新密码与确定密码是否相同
     *      3. 原密码输入是否正确
     *            1. Realm 获取到当前登录的用户模型
     *            2. 根据用户模型中保存的密码匹配用户原密码
     *
     *  2. 利用Realm模型自动更新特性来完成密码的修改
     * @param context
     * @param oldPassWord
     * @param passWord
     * @param passWordConfirm
     * @return
     */
    public static boolean updatePassword(Context context, String oldPassWord, String passWord, String passWordConfirm){

        if(TextUtils.isEmpty(oldPassWord)){
            Toast.makeText(context, "请输入原密码！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(passWord)||!passWord.equals(passWordConfirm)){
            Log.d(TAG, "ChangePassWord: "+passWord+"   "+passWordConfirm+"   :"+TextUtils.isEmpty(passWord)+"  :"+passWord.equals(passWordConfirm));
            Toast.makeText(context, "请输入密码！", Toast.LENGTH_SHORT).show();
            return false;
        }
        Log.d(TAG, "ChangePassWord: 123");
        // 验证原密码是否正确
        RealmHelper realmHelper = new RealmHelper();
        UserModel user = realmHelper.getUser();

        if(!EncryptUtils.encryptMD5ToString(oldPassWord).equals(user.getPassword())){
            Toast.makeText(context, "原密码不正确！", Toast.LENGTH_SHORT).show();
            return false;
        }

        realmHelper.updatePassword(EncryptUtils.encryptMD5ToString(passWord));
        realmHelper.close();
        return  true;
    }
}
