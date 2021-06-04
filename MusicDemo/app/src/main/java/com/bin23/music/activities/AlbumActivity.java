package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bin23.music.R;
import com.bin23.music.adapters.MusicsListAdapter;
import com.bin23.music.helps.RealmHelper;
import com.bin23.music.model.AlbumModel;
import com.bumptech.glide.Glide;

public class AlbumActivity extends BaseActivity {

    public static final String ALBUM_ID = "albumId";
    public static final String ALBUM_NAME = "name";
    public static final String ALBUM_POSTER = "poster";

    private ImageView mIvHeadIcon;
    private TextView mTvPlayListName;
    private RecyclerView mRvList;
    private MusicsListAdapter mMusicsListAdapter;
    
    private String mAlbumId;
    private String mAlbumName;
    private String mAlbumPoster;
    private RealmHelper mRealmHelper;
    private AlbumModel mAlbumModel;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        initData();
        initView();
    }

    private void initData() {
        mAlbumId = getIntent().getStringExtra(ALBUM_ID);
        mAlbumName = getIntent().getStringExtra(ALBUM_NAME);
        mAlbumPoster = getIntent().getStringExtra(ALBUM_POSTER);
        mRealmHelper = new RealmHelper();
        mAlbumModel = mRealmHelper.getAlbum(mAlbumId);
    }

    private void initView() {
        initToolBar(true, "歌单");
        mIvHeadIcon = fd(R.id.iv_playlist_head_left_icon);
        mTvPlayListName = fd(R.id.tv_playlist_name_n);
        mRvList = fd(R.id.rv_list);
        Glide.with(this)
                .load(mAlbumPoster)
                .into(mIvHeadIcon);
        mTvPlayListName.setText(mAlbumName);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRvList.setNestedScrollingEnabled(false);
        // ScrollView和RecyclerView嵌套会出现问题，RecyclerView高度计算不准确
        // 解决
        // 1. 不知道列表高度，需要我们手动计算高度
        //    -  直接在Adapter中计算

        // mMusicsListAdapter = new MusicsListAdapter(this, mRvList);
        mMusicsListAdapter = new MusicsListAdapter(this, mRvList, mAlbumModel.getList());
        mRvList.setAdapter(mMusicsListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmHelper.close();
    }
}