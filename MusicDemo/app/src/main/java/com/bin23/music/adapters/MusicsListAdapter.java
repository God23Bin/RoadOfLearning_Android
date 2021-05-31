package com.bin23.music.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bin23.music.R;

public class MusicsListAdapter extends RecyclerView.Adapter<MusicsListAdapter.ViewHolder> {

    private Context mContext;

    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalculatedRvHeight;

    public MusicsListAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRv = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_musics, parent, false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setRecyclerViewHeight();
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    /**
     * 获取itemView的高度/数量
     * 即可计算出RecyclerView的高度
     */
    private void setRecyclerViewHeight() {
        if (isCalculatedRvHeight || mRv == null) return;

        isCalculatedRvHeight = true;
        // 获取itemView的高度
        RecyclerView.LayoutParams itemViewLp = (RecyclerView.LayoutParams)mItemView.getLayoutParams();
        // 获取itemView的数量
        int itemCount = getItemCount();
        // 计算高度
        int recyclerViewHeight = itemViewLp.height * itemCount;
        // 设置高度
        LinearLayout.LayoutParams rvLp = (LinearLayout.LayoutParams)mRv.getLayoutParams();
        rvLp.height = recyclerViewHeight;
        mRv.setLayoutParams(rvLp);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}