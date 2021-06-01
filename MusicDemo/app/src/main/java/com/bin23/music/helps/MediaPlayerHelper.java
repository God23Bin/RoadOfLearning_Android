package com.bin23.music.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

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
        mPath = path;
        // 1.音乐正在播放，那么重置播放状态
        // 2.设置播放音乐路径
        // 3.准备播放
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.reset();
        }
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.prepareAsync();
        // 监听异步加载，获得异步加载后的通知
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (onMediaPlayerHelperListener != null) {
                    onMediaPlayerHelperListener.onPrepared(mp);
                }
            }
        });
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
    }
}
