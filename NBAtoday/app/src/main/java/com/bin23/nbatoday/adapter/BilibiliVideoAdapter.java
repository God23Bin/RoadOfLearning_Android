package com.bin23.nbatoday.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bin23.nbatoday.R;
import com.bin23.nbatoday.entity.BilibiliVideoBean;
import com.bin23.nbatoday.entity.VideoData;
import com.bin23.nbatoday.utils.VideoUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.jzvd.JzvdStd;

public class BilibiliVideoAdapter extends BaseAdapter {

    private Context context;
//    private List<BilibiliVideoBean.DataBean.ResultBean> mDatas;
    private List<VideoData> mDatas;

//    public BilibiliVideoAdapter(Context context, List<BilibiliVideoBean.DataBean.ResultBean> mDatas) {
//        this.context = context;
//        this.mDatas = mDatas;
//    }

    public BilibiliVideoAdapter(Context context, List<VideoData> mDatas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.tab_community_content_item_lv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 获取指定位置的数据源
        //      1.获取我们所需类型为video的ResultBean
        VideoData videoData = mDatas.get(position);
        holder.upNameTv.setText(videoData.getAuthor());
        holder.playTv.setText(videoData.getPlay() + "");
        holder.favoriteTv.setText(videoData.getFavorites() + "");

        String viedoPageUrl = videoData.getArcurl();
        String purl = "https://upos-sz-mirrorks3.bilivideo.com/upgcxcode/37/58/242135837/242135837-1-208.mp4?e=ig8euxZM2rNcNbhM7wdVhwdlhzKMhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1607482265&gen=playurl&os=ks3bv&oi=978800370&trid=3f30d0d2d4944bac83f872d4d0a55983T&platform=html5&upsig=9e07a039fa6ac733d75455d612948057&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=524602686&orderid=0,1&logo=80000000";

        //      2.设置视频播放的信息
        holder.jzvdStd.setUp(purl, videoData.getTitle(), JzvdStd.SCREEN_NORMAL);
        String picURL = videoData.getPic();
        Picasso.with(context).load(picURL).into(holder.jzvdStd.posterImageView);
        holder.jzvdStd.positionInList = position;
        return convertView;
    }

    static class ViewHolder {
        private JzvdStd jzvdStd;
        private TextView upNameTv, playTv, favoriteTv;

        public ViewHolder(View view){
            jzvdStd = view.findViewById(R.id.item_community_jzvd);
            upNameTv = view.findViewById(R.id.item_community_tv_name);
            playTv = view.findViewById(R.id.item_community_tv_play);
            favoriteTv = view.findViewById(R.id.item_community_tv_favorite);
        }
    }

}
