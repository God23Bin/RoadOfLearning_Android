package com.bin23.music.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * 1.直接在Activity中创建音乐，只会在Activity运行时播放，退出时停止播放，即音乐与Activity绑定
 * 2.通过全局单例类与Application绑定，当Application运行时就播放，被杀死时音乐就停止播放
 * 3.通过后台Service，进行音乐播放，只要Service不被杀死，音乐就不会停止播放
 */
public class MediaPlayerHelper {

    private static MediaPlayerHelper instance;

    private Context mContext;
    private MediaPlayer mMediaPlayer;

    private OnMediaPlayerHelperListener onMediaPlayerHelperListener;

    private String mPath;

    public void setOnMediaPlayerHelperListener(OnMediaPlayerHelperListener onMediaPlayerHelperListener) {
        this.onMediaPlayerHelperListener = onMediaPlayerHelperListener;
    }

    /**
     * 单例器
     * @param context
     * @return
     */
    public static MediaPlayerHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (MediaPlayerHelper.class) {
                if (instance == null) {
                    instance = new MediaPlayerHelper(context);
                }
            }
        }
        return instance;
    }



    private MediaPlayerHelper(Context context) {
        mContext = context;
        mMediaPlayer = new MediaPlayer();
    }

    // MediaPlayer需要拥有什么方法？
    // setPath: 需要知道当前要播放哪首音乐，音乐的播放地址
    // start:   需要播放这首音乐
    // pause:   需要暂停这首音乐

    /**
     * 设置播放哪首音乐
     * @param path
     */
    public void setPath(String path) {
        // 1.音乐正在播放或者切换了音乐，那么重置播放状态
        // 2.设置播放音乐路径
        // 3.准备播放
        if(mMediaPlayer.isPlaying()|| !path.equals(mPath)){
            mMediaPlayer.reset();
            // mMediaPlayer.stop();
        }
        mPath = path;
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.prepareAsync();
//        mMediaPlayer.prepare();
        // 监听异步加载，获得异步加载后的通知
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (onMediaPlayerHelperListener != null) {
                    onMediaPlayerHelperListener.onPrepared(mp);
                }
            }
        });
        // 监听音乐播放完成
        // mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        //     @Override
        //     public void onCompletion(MediaPlayer mp) {
        //         if (onMediaPlayerHelperListener != null) {
        //             onMediaPlayerHelperListener.onCompletion(mp);
        //         }
        //     }
        // });
        
    }

    /**
     * 返回正在播放的音乐路径
     * @return
     */
    public String getPath() {
        return mPath;
    }

    /**
     * 开始播放音乐
     */
    public void start() {
        if (mMediaPlayer.isPlaying()) {
            return;
        }
        mMediaPlayer.start();
    }

    /**
     * 暂停播放音乐
     */
    public void pause() {
        mMediaPlayer.pause();
    }

    public interface OnMediaPlayerHelperListener {
        void onPrepared(MediaPlayer mp);
        // void onCompletion(MediaPlayer mp);
    }
}
