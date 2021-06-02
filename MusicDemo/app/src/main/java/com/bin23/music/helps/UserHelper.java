package com.bin23.music.helps;

/**
 * 用户帮助类
 * 1.用户登录
 *    - 当用户登录时，使用 SharedPreferences 保存登录用户的用户标记
 *    - 使用全局单例类 UserHelper 保存登录用户信息
 *      -- 用户登录之后，如果重新打开App，那么就检查 SharedPreferences 中是否存在登录用户标记
 *      -- 存在->为 UserHelper 赋值，并且进入主页
 *      -- 不存在，则进入登录页面
 * 2.用户退出
 *    - 删除 SharedPreferences 的标记，回到登录页面
 */
public class UserHelper {

    private static UserHelper instance;
    // phone 作为 标记
    private String phone;

    private String username;

    private UserHelper() {

    }

    public static UserHelper getInstance() {
        if (instance == null) {
            synchronized (UserHelper.class) {
                if (instance == null) {
                    instance = new UserHelper();
                }
            }
        }
        return instance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
