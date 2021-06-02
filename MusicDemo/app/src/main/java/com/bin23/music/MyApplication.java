package com.bin23.music;

import android.app.Application;

import com.bin23.music.helps.RealmHelper;
import com.blankj.utilcode.util.Utils;

import io.realm.Realm;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化 AndroidUtilCode
        Utils.init(this);
        // 初始化 Realm
        Realm.init(this);
        // 数据迁移/版本升级
//        RealmHelper.migration();
    }
}
