//package com.bin23.nbatoday.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bin23.nbatoday.MainActivity;
//import com.bin23.nbatoday.R;
//import com.bin23.nbatoday.entity.NewsBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class NewsListAdapter extends BaseAdapter {
//
//    private Context applicationContext;
//    private List<NewsBean> adapterArrayList = new ArrayList<>();
//
//    public NewsListAdapter(Context applicationContext, List<NewsBean> adapterArrayList) {
//        this.applicationContext = applicationContext;
//        this.adapterArrayList = adapterArrayList;
//    }
//
//    public Context getApplicationContext() {
//        return applicationContext;
//    }
//
//    public void setApplicationContext(Context applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    @Override
//    public int getCount() {
//        return adapterArrayList.size();
//    }
//
//    @Override
//    public NewsBean getItem(int position) {
//        return adapterArrayList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(applicationContext).inflate(R.layout.tab_home_content_infolist_lv, null);// 将布局转换为 View 的方法
//            holder = new ViewHolder(convertView);
//            holder.h_tv_title = (TextView) convertView.findViewById(R.id.h_tv_title);
//            holder.h_tv_des = (TextView) convertView.findViewById(R.id.h_tv_des);
//            holder.h_iv_img = (ImageView) convertView.findViewById(R.id.h_iv_img);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        // 加载控件显示的内容
//        // 获取集合指定位置的数据
//        NewsBean item = getItem(position);
//        holder.h_tv_title.setText(item.getNewsTitle());
//        holder.h_tv_des.setText(item.getNewsDes());
//        holder.h_iv_img.setImageResource(item.getNewsPic());
//        return convertView;
//    }
//
//    private static class ViewHolder {
//        ImageView h_iv_img;
//        TextView h_tv_title;
//        TextView h_tv_des;
//        public ViewHolder(View view){
//            h_iv_img = view.findViewById(R.id.h_iv_img);
//            h_tv_title = view.findViewById(R.id.h_tv_title);
//            h_tv_des = view.findViewById(R.id.h_tv_des);
//        }
//    }
//}
//
//
//
