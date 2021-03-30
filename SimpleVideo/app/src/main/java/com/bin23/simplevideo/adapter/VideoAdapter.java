package com.bin23.simplevideo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bin23.simplevideo.MainActivity;
import com.bin23.simplevideo.R;
import com.bin23.simplevideo.entity.VideoBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.jzvd.JzvdStd;

public class VideoAdapter extends BaseAdapter {

    private Context context;
    private List<VideoBean.ItemListEntity> mDatas;

    public VideoAdapter(Context context, List<VideoBean.ItemListEntity> mDatas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main_lv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 获取指定位置的数据源
        VideoBean.ItemListEntity.DataEntity dataEntity = mDatas.get(position).getData();
        VideoBean.ItemListEntity.DataEntity.AuthorEntity author = dataEntity.getAuthor();
        holder.nameTv.setText(author.getName());
        holder.desTv.setText(author.getDescription());
        String iconURL = author.getIcon();
        if (!TextUtils.isEmpty(iconURL)) {
            Picasso.with(context).load(iconURL).into(holder.iconIv);
        }
        // 获取点赞数和评论数
        VideoBean.ItemListEntity.DataEntity.ConsumptionEntity consumptionEntity = dataEntity.getConsumption();
        holder.heartTv.setText(consumptionEntity.getRealCollectionCount() + "");
        holder.commentTv.setText(consumptionEntity.getReplyCount() + "");

        // 设置视频播放的信息
        holder.jzvdStd.setUp(dataEntity.getPlayUrl(), dataEntity.getTitle(), JzvdStd.SCREEN_NORMAL);
        String thumbURL = dataEntity.getCover().getFeed(); // 获取缩略图的网络地址
        Picasso.with(context).load(thumbURL).into(holder.jzvdStd.posterImageView);
        holder.jzvdStd.positionInList = position;
        return convertView;
    }

    class ViewHolder {
        private JzvdStd jzvdStd;
        private ImageView iconIv;
        TextView nameTv, desTv, heartTv, commentTv;
        public ViewHolder (View view){
            jzvdStd = view.findViewById(R.id.item_main_jzvd);
            iconIv = view.findViewById(R.id.item_main_iv);
            nameTv = view.findViewById(R.id.item_main_tv_name);
            desTv = view.findViewById(R.id.item_main_tv_des);
            heartTv = view.findViewById(R.id.item_main_tv_heart);
            commentTv = view.findViewById(R.id.item_main_tv_comment);
        }
    }
}
