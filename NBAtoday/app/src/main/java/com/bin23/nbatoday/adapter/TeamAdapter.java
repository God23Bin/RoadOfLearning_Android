package com.bin23.nbatoday.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bin23.nbatoday.R;
import com.bin23.nbatoday.entity.TeamBean;

import java.util.List;

public class TeamAdapter extends BaseAdapter {
    private Context context;
    private List<TeamBean> mDatas;

    public TeamAdapter(Context context, List<TeamBean> mDatas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.zone_view_gridlist_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 获取当前指定位置的数据
        TeamBean teamBean = mDatas.get(position);
        holder.iv.setImageResource(teamBean.getTeamPic());
        holder.nameTv.setText(teamBean.getTeamName());
        holder.desTv.setText(teamBean.getTeamFans());

        return convertView;
    }

    static class ViewHolder {
        ImageView iv;
        TextView nameTv, desTv;
        public ViewHolder(View view){
            iv = view.findViewById(R.id.zone_list_item_iv);
            nameTv = view.findViewById(R.id.zone_list_item_name_tv);
            desTv = view.findViewById(R.id.zone_list_item_des_tv);
        }
    }
}
