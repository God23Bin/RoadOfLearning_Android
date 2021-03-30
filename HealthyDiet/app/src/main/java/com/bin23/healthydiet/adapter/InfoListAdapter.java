package com.bin23.healthydiet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bin23.healthydiet.R;
import com.bin23.healthydiet.bean.FoodBean;

import java.util.List;

public class InfoListAdapter extends BaseAdapter {
    private Context context;
    private List<FoodBean> mDatas;

    public InfoListAdapter(Context context, List<FoodBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    /**
     * 集合的长度就代表展示了有多少行，因为行数取决于集合的长度
     * @return
     */
    @Override
    public int getCount() {
        return mDatas.size();
    }

    /**
     * 返回指定位置所对应的数据
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * 返回指定位置所对应的id
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 返回指定位置所对应的View
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        // 判断是否有滑出过的资源，有就复用
        if (convertView == null) {
            // 为空，说明第一次滑到这里
            convertView = LayoutInflater.from(context).inflate(R.layout.item_infolist_lv, null);// 将布局转换为 View 的方法
            holder = new ViewHolder(convertView);
            // 使用holder是为了减少我们findViewById的次数，减少内存消耗
            // 如何让holder知道第一次创建后就认为已经存在这个View了呢？
            // 使用Tag方法
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 加载控件显示的内容
        // 获取集合指定位置的数据
        FoodBean foodBean = mDatas.get(position);
        holder.titleTv.setText(foodBean.getTitle());
        holder.notTv.setText("不可匹配：" + foodBean.getNotmatch());
        holder.iv.setImageResource(foodBean.getPicId());
        return convertView;
    }

    class ViewHolder{
        ImageView iv;
        TextView titleTv, notTv;
        public ViewHolder(View view){
            iv = view.findViewById(R.id.item_info_iv);
            titleTv = view.findViewById(R.id.item_info_tv_title);
            notTv = view.findViewById(R.id.item_info_tv_notmatch);

        }
    }
}
