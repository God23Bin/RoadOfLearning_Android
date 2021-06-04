// package com.bin23.music.services;

// import android.app.Service;
// import android.content.Intent;
// import android.media.MediaPlayer;
// import android.os.Binder;
// import android.os.IBinder;

// import com.bin23.music.helps.MediaPlayerHelper;
// import com.bin23.music.model.MusicModel;

// /**
//  * 1.通过Service连接 PlayMusicView 和 MediaPlayerHelper
//  * 2.怎么连接？ 
//  *   PlayMusicView - Service
//  *      1).播放音乐、暂停音乐
//  *      2).控制启动Service、绑定Service、解除绑定Service
//  * 3.MediaPlayerHelper - Service
//  *      1).播放音乐、暂停音乐
//  *      2).监听音乐播放完成的方法，通过回调这个方法停止Service
//  * 
//  */
// public class MusicService extends Service {

//     private MediaPlayerHelper mMediaPlayerHelper;
//     private MusicModel mMusicModel;

//     public MusicService() {
//     }

//     public class MusicBind extends Binder {
//         /**
//          * 它需要知道它播放哪首音乐
//          * 
//          * 设置音乐 (MusicModel)
//          */
//         public void setMusic(MusicModel musicModel) {
//             mMusicModel = musicModel;
//         }

//         /**
//          * 播放音乐
//          */
//         public void playMusic() {
//             if (mMediaPlayerHelper.getPath() != null && mMediaPlayerHelper.getPath().equals(mMusicModel.getPath())) {
//                 mMediaPlayerHelper.start();
//             } else {
//                 mMediaPlayerHelper.setPath(mMusicModel.getPath());
//                 // 监听当前要播放的音乐是否准备完成了，完成就执行start()
//                 mMediaPlayerHelper.setOnMediaPlayerHelperListener(new MediaPlayerHelper.OnMediaPlayerHelperListener() {
//                     @Override
//                     public void onPrepared(MediaPlayer mp) {
//                         mMediaPlayerHelper.start();
//                     }
//                     @Override
//                     public void onCompletion(MediaPlayer mp) {
//                         stopSelf();
//                     }
//                 });
//             }
//         }

//         /**
//          * 暂停播放
//          */
//         public void stopMusic() {
//             mMediaPlayerHelper.pause();
//         }

//     }

//     @Override
//     public IBinder onBind(Intent intent) {
        
//         return new MusicBind();
//     }

//     @Override
//     public void onCreate() {
//         super.onCreate();
//         mMediaPlayerHelper = MediaPlayerHelper.getInstance(this);
//     }
// }
