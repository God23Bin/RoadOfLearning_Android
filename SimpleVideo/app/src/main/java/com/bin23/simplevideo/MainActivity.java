package com.bin23.simplevideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.bin23.simplevideo.adapter.VideoAdapter;
import com.bin23.simplevideo.entity.VideoBean;
import com.bin23.simplevideo.utils.HttpUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JzvdStd;

public class MainActivity extends AppCompatActivity {

    private ListView mainLv;
    private String url = "http://baobab.kaiyanapp.com/api/v4/tabs/selected?num=2";
    private List<VideoBean.ItemListEntity> mDatas;
    private VideoAdapter adapter;

    // 消息通知机制所需
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        /**
         * 重写接收方法
         * @param msg
         */
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                String json = (String) msg.obj;
                VideoBean videoBean = new Gson().fromJson(json, VideoBean.class);
                List<VideoBean.ItemListEntity> itemList = videoBean.getItemList();
                // 遍历一下，排除video类型以外的类型，也就是过滤不需要的数据
                for (int i = 0; i < itemList.size(); i++) {
                    VideoBean.ItemListEntity listEntity = itemList.get(i);
                    // 如果这个类型是video，那么就是我们想要的
                    if (listEntity.getType().equals("video")) {
                        mDatas.add(listEntity);
                    }
                }
                // 提示适配器更新数据
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("开眼视频");
        mainLv = findViewById(R.id.main_lv);
        // 数据源
        mDatas = new ArrayList<>();

        // 创建适配器对象
        adapter = new VideoAdapter(this, mDatas);

        // 设置适配器
        mainLv.setAdapter(adapter);

        // 加载网络数据
        loadData();

    }

    private void loadData() {
        // 创建新的线程，完成数据的获取
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonConetent = HttpUtil.getJsonConetent(url);
                // 子线程不能更新UI，需要通过handler发送数据回到主线程--消息通知机制
                Message message = new Message(); // 发送的消息对象
                message.what = 1; // 设置消息编号
                message.obj = jsonConetent;
                handler.sendMessage(message); // 子线程发送回主线程

            }
        }).start(); //-----------------------------不要忘了start()
    }

    @Override
    protected void onStop() {
        super.onStop();
        JzvdStd.releaseAllVideos();    //释放正在被播放的视频信息
    }
}