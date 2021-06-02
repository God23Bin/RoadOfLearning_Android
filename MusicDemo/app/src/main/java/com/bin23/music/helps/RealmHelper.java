package com.bin23.music.helps;

import android.content.Context;

//import com.bin23.music.migration.Migration;
//import com.bin23.music.model.AlbumModel;
//import com.bin23.music.model.MusicModel;
//import com.bin23.music.model.MusicSourceModel;
import com.bin23.music.model.UserModel;
import com.bin23.music.utils.DataUtil;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class RealmHelper {

    private Realm mRealm;

    public RealmHelper() {
        mRealm = Realm.getDefaultInstance();
    }

//    /**
//     * Realm数据库发生结构性变化（模型或者模型中的字段发生了新增、修改、删除）的时候，我们就需要对数据库进行迁移
//     *
//     * @return
//     */
//    // 返回RealmConfiguration
//    private static RealmConfiguration getRealmConf() {
//        return new RealmConfiguration.Builder()
//                .schemaVersion(1)
//                .migration(new Migration())
//                .build();
//    }

//    /**
//     * 告诉Reaml数据需要迁移，并设置新版本号
//     */
//    public static void migration() {
//        RealmConfiguration conf = getRealmConf();
//        // Realm 设置最新的配置
//        Realm.setDefaultConfiguration(conf);
//        // 告诉Realm数据需要迁移
//        try {
//            Realm.migrateRealm(conf);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 关闭数据库
     */
    public void close() {
        if (mRealm != null || !mRealm.isClosed())
            mRealm.close();
    }

    /**
     * 保存用户信息
     *
     * @param userModel
     */
    public void saveUser(UserModel userModel) {
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        mRealm.commitTransaction();
    }

    public List<UserModel> getAllUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        return query.findAll();
    }

    /**
     * 验证用户，用于登录，查询输入的手机号与密码与数据库中的是否匹配
     *
     * @param phone
     * @param password
     * @return
     */
    public boolean validateUser(String phone, String password) {
        boolean res = false;
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query = query.equalTo("phone", phone).equalTo("password", password);
        UserModel user = query.findFirst();
        if (user != null) {
            res = true;
        }
        return res;
    }

    /**
     * 获取当前用户
     */
    public UserModel getUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        return query.equalTo("phone", UserHelper.getInstance().getPhone()).findFirst();
    }

    /**
     * 修改密码
     */
    public void updatePassword(String password) {
        UserModel userModels = getUser();
        mRealm.beginTransaction();
        userModels.setPassword(password);
        mRealm.commitTransaction();
    }

//    /**
//     * 用户登录，存放数据
//     * 用户退出，删除数据
//     */
//
//    /**
//     * 保存音乐源数据
//     *
//     * @param context
//     */
//    public void setMusicSource(Context context) {
//        // 拿到资源文件中的数据
//        String musicSourceJson = DataUtil.getJsonFromAssets(context, "DataSource.json");
//        mRealm.beginTransaction();
//        mRealm.createObjectFromJson(MusicSourceModel.class, musicSourceJson);
//        mRealm.commitTransaction();
//    }
//
//    /**
//     * 删除音乐元数据
//     */
//    public void removeMusicSource() {
//        mRealm.beginTransaction();
//        mRealm.delete(MusicSourceModel.class);
//        mRealm.delete(MusicModel.class);
//        mRealm.delete(AlbumModel.class);
//        mRealm.commitTransaction();
//    }
//
//    /**
//     * 返回音乐源数据
//     */
//    public MusicSourceModel getMusicSource() {
//        return mRealm.where(MusicSourceModel.class).findFirst();
//    }
//
//
//    /**
//     * 返回歌单
//     * @param albumId
//     * @return
//     */
//    public AlbumModel getAlbum(String albumId) {
//        return mRealm.where(AlbumModel.class).equalTo("albumId", albumId).findFirst();
//    }
//
//    /**
//     * 返回音乐
//     * @param musicId
//     * @return
//     */
//    public MusicModel getMusic(String musicId) {
//        return mRealm.where(MusicModel.class).equalTo("musicId", musicId).findFirst();
//    }

}
