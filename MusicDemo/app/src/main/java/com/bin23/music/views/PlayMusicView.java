package com.bin23.music.views;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bin23.music.R;
import com.bin23.music.helps.MediaPlayerHelper;
import com.bin23.music.model.MusicModel;
// import com.bin23.music.services.MusicService;
import com.bumptech.glide.Glide;

public class PlayMusicView extends FrameLayout {

    private Context mContext;

    private View mView;

    private ImageView mIvCoverIcon;

    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;

    private FrameLayout mFlPlayMusic;
    private ImageView mIvNeedle;

    // 播放按钮
    private ImageView mIvMusicPlay;
    // 播放状态
    private boolean isPlaying;
    // 音乐播放帮助类
    private MediaPlayerHelper mMediaPlayerHelper;

    private String mPath;

    // private Intent mServiceIntent;
    // private boolean isBindingService;
    // private MusicService.MusicBind mMusicBind;
    // private MusicModel mMusicModel;

    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        // 还是一样生成一个View，通过Layout
        mView = LayoutInflater.from(mContext).inflate(R.layout.play_music, this, false);

        mIvCoverIcon = mView.findViewById(R.id.iv_circle_cover_icon);
        mIvMusicPlay = mView.findViewById(R.id.iv_music_play);


        /**
         * 1. 定义所需要执行的动画
         *      - 唱片转动的动画
         *      - 指针指向唱片的动画
         *      - 指针离开唱片的动画
         * 2. 安卓为View提供的startAnimation方法，使View执行动画
         */

        // 初始化创建的动画
        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_animation);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);
        // 接下来调用 安卓为View提供的startAnimation方法，先声明下需要执行动画的View，写在了上面
        mFlPlayMusic = mView.findViewById(R.id.fl_playMusic);
        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();
            }
        });
        mIvNeedle = mView.findViewById(R.id.iv_needle);

        addView(mView);

        mMediaPlayerHelper = MediaPlayerHelper.getInstance(mContext);
    }

    /**
     * 设置唱片中显示的音乐封面
     * @param iconUrl
     */
    public void setMusicCover(String iconUrl) {
    // public void setMusicCover() {
        Glide.with(mContext)
                .load(iconUrl)
                // .load(mMusicModel.getPoster())
                .into(mIvCoverIcon);
    }


    // public void setMusic(MusicModel musicModel) {
    //     mMusicModel = musicModel;
    //     setMusicCover();
    // }

    /**
     * 切换播放状态
     */
    private void trigger() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic(mPath);
            // playMusic();
        }
    }

    /**
     * 播放音乐执行动画效果，并播放音乐
     */
    public void playMusic(String path) {
    // public void playMusic() {
        mPath = path;
        isPlaying = true;
        mIvMusicPlay.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvNeedle.startAnimation(mPlayNeedleAnim);
        // 1.判断当前音乐是否已经是在播放的音乐
        // 2.如果当前的音乐是在播放的音乐，那么直接执行start()
        // 3.如果当前音乐不是需要播放的音乐的时候，直接setPath()
        if (mMediaPlayerHelper.getPath() != null && mMediaPlayerHelper.getPath().equals(path)) {
            mMediaPlayerHelper.start();
        } else {
            mMediaPlayerHelper.setPath(path);
            // 监听当前要播放的音乐是否准备完成了，完成就执行start()
            mMediaPlayerHelper.setOnMediaPlayerHelperListener(new MediaPlayerHelper.OnMediaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayerHelper.start();
                }
            });
        }
        // startMusicService();
    }

    public void stopMusic() {
        isPlaying = false;
        mIvMusicPlay.setVisibility(View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        mIvNeedle.startAnimation(mStopNeedleAnim);

        mMediaPlayerHelper.pause();
        // if (mMusicBind != null)
        //     mMusicBind.stopMusic();
    }

    // /**
    //  * 启动音乐服务
    //  */
    // private void startMusicService() {
    //     // 启动Service
    //     if (mServiceIntent == null) {
    //         mServiceIntent = new Intent(mContext, MusicService.class);
    //         mContext.startService(mServiceIntent);
    //     } else {
    //         mMusicBind.playMusic();
    //     }
    //     // 绑定Service，判断当前Service是否判定？绑定：解绑
    //     if (!isBindingService) {
    //         isBindingService = true;
    //         // 需要一个Connection
    //         mContext.bindService(mServiceIntent, conn, Context.BIND_AUTO_CREATE);
    //     }

    // }

    // ServiceConnection conn = new ServiceConnection() {
    //     @Override
    //     public void onServiceConnected(ComponentName name, IBinder service) {
    //         mMusicBind = (MusicService.MusicBind)service;
    //         mMusicBind.setMusic(mMusicModel);
    //         mMusicBind.playMusic();
    //     }

    //     @Override
    //     public void onServiceDisconnected(ComponentName name) {

    //     }
    // };

    // /**
    //  * 取消音乐绑定
    //  */
    // public void destory() {
    //     // 如果绑定了服务，那么解除绑定
    //     if (isBindingService) {
    //         isBindingService = false;
    //         mContext.unbindService(conn);
    //     }
    // }
}
