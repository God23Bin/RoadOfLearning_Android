package com.bin23.nbatoday.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bin23.nbatoday.R;
import com.bin23.nbatoday.entity.NewsEntity;
import com.bin23.nbatoday.utils.HTTPUtil;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<NewsEntity.ResultBean.DataBean> mDatas;

    public NewsAdapter(Context context, List<NewsEntity.ResultBean.DataBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tab_home_content_infolist_lv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 获取指定位置的数据
        NewsEntity.ResultBean.DataBean dataBean = mDatas.get(position);
        String picURL = dataBean.getThumbnail_pic_s();
        String title = dataBean.getTitle();
        String author_name = dataBean.getAuthor_name();
        String date = dataBean.getDate();

        // 解析图片地址，获取图片
        getBitmap(holder, picURL);
//        holder.newsIv.setImageBitmap(bitmap);
        holder.newsTitleTv.setText(title);
        holder.authorTv.setText(author_name);
        holder.timeTv.setText(date);

        return convertView;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 3) {
                List list = (List) msg.obj;
                Bitmap bitmap = (Bitmap) list.get(0);
                ViewHolder holder = (ViewHolder) list.get(1);
                holder.newsIv.setImageBitmap(bitmap);

            }
        }
    };

    private void getBitmap(ViewHolder viewHolder, String picURL) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap httpBitmap = HTTPUtil.getHttpBitmap(picURL);
                List list = new ArrayList();
                list.add(httpBitmap);
                list.add(viewHolder);
                Message message = new Message();
                message.what = 3;
                message.obj = list;
                handler.sendMessage(message);
            }
        }).start();
    }

    static class ViewHolder {
        private ImageView newsIv;
        private TextView authorTv;
        private TextView newsTitleTv;
        private TextView timeTv;

        public ViewHolder (View view){
            newsIv = view.findViewById(R.id.h_iv_img);
            newsTitleTv = view.findViewById(R.id.h_tv_news_title);
            authorTv = view.findViewById(R.id.h_tv_author);
            timeTv = view.findViewById(R.id.h_tv_time);
        }
    }
}
