package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bin23.music.R;
import com.bin23.music.adapters.MusicsListAdapter;

public class AlbumActivity extends BaseActivity {

    private RecyclerView mRvList;
    private MusicsListAdapter mMusicsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        initView();
    }

    private void initView() {
        initToolBar(true, "歌单");

        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRvList.setNestedScrollingEnabled(false);
        // ScrollView和RecyclerView嵌套会出现问题，RecyclerView高度计算不准确
        // 解决
        // 1. 不知道列表高度，需要我们手动计算高度
        //    -  直接在Adapter中计算

        mMusicsListAdapter = new MusicsListAdapter(this, mRvList);
        mRvList.setAdapter(mMusicsListAdapter);
    }
}