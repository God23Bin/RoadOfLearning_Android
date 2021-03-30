package com.bin23.nbatoday.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bin23.nbatoday.R;
import com.bin23.nbatoday.adapter.NewsAdapter;
import com.bin23.nbatoday.entity.NewsEntity;
import com.bin23.nbatoday.utils.HTTPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeNewsFragment extends Fragment {

    private ListView homeNewsLv;
    private String url = "http://v.juhe.cn/toutiao/index?type=tiyu&key=e357dbe7418a4c1768a0c6fed0633f12";
    private List<NewsEntity.ResultBean.DataBean> mDatas;

    private NewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home_content, container, false);

        homeNewsLv = view.findViewById(R.id.home_news_infolist_lv);

        // 数据源
        mDatas = new ArrayList<>();
        // 创建适配器
        adapter = new NewsAdapter(view.getContext(), mDatas);
        // 设置适配器
        homeNewsLv.setAdapter(adapter);

        loadData();
        initUI();
        return view;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 2) {
                String json = (String) msg.obj;
                NewsEntity newsEntity = new Gson().fromJson(json, NewsEntity.class);
                NewsEntity.ResultBean resultBean = newsEntity.getResult();
                List<NewsEntity.ResultBean.DataBean> itemList = resultBean.getData();
                mDatas.addAll(itemList);
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
                message.what = 2; // 设置消息编号
                message.obj = jsonConetent;
                handler.sendMessage(message); // 子线程发送回主线程
            }
        }).start(); //-----------------------------不要忘了start()
    }

    private void initUI() {
        homeNewsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mDatas.get(position).getUrl()));
                startActivity(intent);
            }
        });
    }


}
