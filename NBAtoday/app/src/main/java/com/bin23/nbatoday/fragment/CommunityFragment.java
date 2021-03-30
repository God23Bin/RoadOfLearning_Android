package com.bin23.nbatoday.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bin23.nbatoday.R;
import com.bin23.nbatoday.adapter.BilibiliVideoAdapter;
import com.bin23.nbatoday.entity.BilibiliVideoBean;
import com.bin23.nbatoday.entity.VideoData;
import com.bin23.nbatoday.utils.HTTPUtil;
import com.bin23.nbatoday.utils.VideoUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JzvdStd;

public class CommunityFragment extends Fragment {

    private ListView communityLv;
    private String url = "https://api.bilibili.com/x/web-interface/search/all/v2?keyword=nba";
//    private List<BilibiliVideoBean.DataBean.ResultBean> mDatas;
    private List<VideoData> mDatas;

    private BilibiliVideoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_community_content, container, false);
        communityLv = view.findViewById(R.id.community_lv);

        // 数据源
        mDatas = new ArrayList<>();
        // 创建适配器
        adapter = new BilibiliVideoAdapter(view.getContext(), mDatas);
        // 设置适配器
        communityLv.setAdapter(adapter);

        // 加载网络数据
        loadData();


        return view;
    }


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                String json = (String) msg.obj;
                BilibiliVideoBean videoBean = new Gson().fromJson(json, BilibiliVideoBean.class);
                BilibiliVideoBean.DataBean dataBean = videoBean.getData();
                List<BilibiliVideoBean.DataBean.ResultBean> resultBeanList_fromJson = dataBean.getResult();
                BilibiliVideoBean.DataBean.ResultBean resultBean = VideoUtil.getResultBeanOfVideo(resultBeanList_fromJson);
                // 获取视频数据
                List<VideoData> itemList = resultBean.getData();
                for (int i = 0; i < itemList.size(); i++) {
                    VideoData videoData = itemList.get(i);
                    mDatas.add(videoData);
                }
                // 提示适配器更新数据
                adapter.notifyDataSetChanged();
            }
        }
    };


    private void loadData() {
        // 创建新的线程，完成数据的获取
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonConetent = HTTPUtil.getJsonConetent(url);
                // 子线程不能更新UI，需要通过handler发送数据回到主线程--消息通知机制
                Message message = new Message(); // 发送的消息对象
                message.what = 1; // 设置消息编号
                message.obj = jsonConetent;
                handler.sendMessage(message); // 子线程发送回主线程
            }
        }).start(); //-----------------------------不要忘了start()
    }


}
