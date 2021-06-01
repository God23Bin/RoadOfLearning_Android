package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bin23.music.R;
import com.bin23.music.views.PlayMusicView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    private ImageView mIvBg;

    private PlayMusicView mPlayMusicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        // 隐藏statusBar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        initView();
    }

    private void initView() {
        mIvBg = fd(R.id.iv_bg);

        Glide.with(this)
                .load("https://th.bing.com/th/id/Rafa07faa2f0e044cd86319d5bb7f9908?rik=HVdVHQur3UAvrw&riu=http%3a%2f%2fp3.music.126.net%2fNsMBYe-88XPS0BZjGH4opw%3d%3d%2f7710875046485282.jpg&ehk=6L5vfizAnxM9rhIZC6ypH64wnwLzQ%2ftEVfZO5pcRnzo%3d&risl=&pid=ImgRaw")
                // 提供applay()方法
                //   - 接收RequestOptions参数，可以对图片进行配置，有bitmapTransform()方法将Transformation封装到RequestOptions对象里面，而RequestOptions实现Transformation
                // bitmapTransform()方法
                //   - 接收TransFormation参数，我们可以new出BlurTransformation()进行高斯模糊处理，其参数一，模糊程度，参数二，宽高为原图片十分之一
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(80,3)))
                .into(mIvBg);

        mPlayMusicView = fd(R.id.play_music_view);
        mPlayMusicView.setMusicCover("https://th.bing.com/th/id/Rafa07faa2f0e044cd86319d5bb7f9908?rik=HVdVHQur3UAvrw&riu=http%3a%2f%2fp3.music.126.net%2fNsMBYe-88XPS0BZjGH4opw%3d%3d%2f7710875046485282.jpg&ehk=6L5vfizAnxM9rhIZC6ypH64wnwLzQ%2ftEVfZO5pcRnzo%3d&risl=&pid=ImgRaw");
        mPlayMusicView.playMusic("https://m701.music.126.net/20210601013130/3dc5a044c299995f405ec25b6014e7b6/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/8698723562/a8df/92f7/6c9a/eb4cceb24d8b642f0dfe72ee4e020537.mp3");
    }

    // 后退按钮点击事件
    public void onBackClick(View view){
        onBackPressed();
    }
}