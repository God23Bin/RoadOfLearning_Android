package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bin23.music.R;
import com.bin23.music.helps.RealmHelper;
import com.bin23.music.model.MusicModel;
import com.bin23.music.views.PlayMusicView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    public static final String MUSIC_ID = "musicId";

    private ImageView mIvBg;
    private TextView mTvName, mTvAuthor;

    private PlayMusicView mPlayMusicView;

    private String mMusicId;
    private MusicModel mMusicModel;
    private RealmHelper mRealmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        // 隐藏statusBar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        initView();
    }

    private void initData() {
        mMusicId = getIntent().getStringExtra(MUSIC_ID);
        mRealmHelper = new RealmHelper();
        mMusicModel = mRealmHelper.getMusic(mMusicId);
    }

    private void initView() {
        mIvBg = fd(R.id.iv_bg);
        mTvName = fd(R.id.tv_name);
        mTvAuthor = fd(R.id.tv_author);

        Glide.with(this)
                .load(mMusicModel.getPoster())
                // .load("https://th.bing.com/th/id/Rafa07faa2f0e044cd86319d5bb7f9908?rik=HVdVHQur3UAvrw&riu=http%3a%2f%2fp3.music.126.net%2fNsMBYe-88XPS0BZjGH4opw%3d%3d%2f7710875046485282.jpg&ehk=6L5vfizAnxM9rhIZC6ypH64wnwLzQ%2ftEVfZO5pcRnzo%3d&risl=&pid=ImgRaw")
                // 提供applay()方法
                //   - 接收RequestOptions参数，可以对图片进行配置，有bitmapTransform()方法将Transformation封装到RequestOptions对象里面，而RequestOptions实现Transformation
                // bitmapTransform()方法
                //   - 接收TransFormation参数，我们可以new出BlurTransformation()进行高斯模糊处理，其参数一，模糊程度，参数二，宽高为原图片十分之一
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(80,3)))
                .into(mIvBg);

        mTvName.setText(mMusicModel.getName());
        mTvAuthor.setText(mMusicModel.getAuthor());

        mPlayMusicView = fd(R.id.play_music_view);
        // mPlayMusicView.setMusicCover("https://th.bing.com/th/id/Rafa07faa2f0e044cd86319d5bb7f9908?rik=HVdVHQur3UAvrw&riu=http%3a%2f%2fp3.music.126.net%2fNsMBYe-88XPS0BZjGH4opw%3d%3d%2f7710875046485282.jpg&ehk=6L5vfizAnxM9rhIZC6ypH64wnwLzQ%2ftEVfZO5pcRnzo%3d&risl=&pid=ImgRaw");
        mPlayMusicView.setMusicCover(mMusicModel.getPoster());
        // mPlayMusicView.playMusic("https://m801.music.126.net/20210604093609/1502b383d1dfb07cd2a1263e8eab901b/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/7937681993/d14b/8f71/1390/e694a66f322ff46b6945edc18a621966.mp3");
        
        // mPlayMusicView.playMusic("https://m701.music.126.net/20210602100116/2806dc0aec7d895bbdbbc51b83d95dee/jdymusic/obj/w5zDlMODwrDDiGjCn8Ky/1682377598/cf45/a902/f89d/404b4307336ce2cb7924d278e67c3adf.mp3");
        // System.out.println();
        // System.out.println();
        // System.out.println();
        // System.out.println();
        System.out.println("-------------------------------------------" + mMusicModel.getPath());
        // System.out.println();System.out.println();System.out.println();
        mPlayMusicView.playMusic(mMusicModel.getPath());

        // mPlayMusicView.setMusic(mMusicModel);
        // mPlayMusicView.playMusic();
    }

    // 后退按钮点击事件
    public void onBackClick(View view){
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解绑Service
        // mPlayMusicView.destory();
        mRealmHelper.close();
    }
}