package com.bin23.music.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bin23.music.R;
import com.bin23.music.activities.PlayMusicActivity;
import com.bin23.music.model.MusicModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class MusicsListAdapter extends RecyclerView.Adapter<MusicsListAdapter.ViewHolder> {

    private Context mContext;

    private View mItemView;
    private RecyclerView mRv;
    private boolean isCalculatedRvHeight;

    private List<MusicModel> mDataSource;

    public MusicsListAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRv = recyclerView;
    }

    public MusicsListAdapter(Context context, RecyclerView recyclerView, List<MusicModel> dataSource) {
        mContext = context;
        mRv = recyclerView;
        mDataSource = dataSource;
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

        final MusicModel musicModel = mDataSource.get(position);

        Glide.with(mContext)
                // .load("https://th.bing.com/th/id/Ra1ad377e593da8811d0a417628a1a789?rik=r9Eelwbi19NwTA&riu=http%3a%2f%2fp4.music.126.net%2fmzMD4FTkPStfqefkoiy3Eg%3d%3d%2f3222668581393466.jpg&ehk=OR3Eq4mc0ZRtJOVOGpNMqUsBxHTTpjKQ5N%2fFYLPs1c4%3d&risl=&pid=ImgRaw")
                .load(musicModel.getPoster())
                .into(holder.ivIcon);

        holder.tvName.setText(musicModel.getName());
        holder.tvAuthor.setText(musicModel.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayMusicActivity.class);
                intent.putExtra(PlayMusicActivity.MUSIC_ID, musicModel.getMusicId());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // return 20;
        return mDataSource.size();
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

        ImageView ivIcon;
        View itemView;
        TextView tvName, tvAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            ivIcon = itemView.findViewById(R.id.iv_song_cover_icon);
            tvName = itemView.findViewById(R.id.tv_song_name);
            tvAuthor = itemView.findViewById(R.id.tv_song_author);
        }
    }
}