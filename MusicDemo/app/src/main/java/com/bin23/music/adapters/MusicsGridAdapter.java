package com.bin23.music.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bin23.music.R;
import com.bin23.music.activities.AlbumActivity;
import com.bin23.music.model.AlbumModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class MusicsGridAdapter extends RecyclerView.Adapter<MusicsGridAdapter.ViewHolder> {

    private Context mContext;
    private List<AlbumModel> mDataSource;

    public MusicsGridAdapter(Context context) {
        mContext = context;
    }

    public MusicsGridAdapter(Context context, List<AlbumModel> dataSource) {
        mContext = context;
        mDataSource = dataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_musics, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final AlbumModel albumModel = mDataSource.get(position);

        Glide.with(mContext)
                // .load("https://th.bing.com/th/id/Ra1ad377e593da8811d0a417628a1a789?rik=r9Eelwbi19NwTA&riu=http%3a%2f%2fp4.music.126.net%2fmzMD4FTkPStfqefkoiy3Eg%3d%3d%2f3222668581393466.jpg&ehk=OR3Eq4mc0ZRtJOVOGpNMqUsBxHTTpjKQ5N%2fFYLPs1c4%3d&risl=&pid=ImgRaw")
                .load(albumModel.getPoster())
                .into(holder.ivPlaylistCoverIcon);
        holder.mTvPlayNum.setText(albumModel.getPlayNum());
        holder.mTvName.setText(albumModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AlbumActivity.class);
                intent.putExtra(AlbumActivity.ALBUM_ID, albumModel.getAlbumId());
                intent.putExtra(AlbumActivity.ALBUM_NAME, albumModel.getName());
                intent.putExtra(AlbumActivity.ALBUM_POSTER, albumModel.getPoster());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // return 99;
        return mDataSource.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPlaylistCoverIcon;
        View itemView;
        TextView mTvPlayNum, mTvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivPlaylistCoverIcon = itemView.findViewById(R.id.iv_playlist_cover_icon);
            mTvPlayNum = itemView.findViewById(R.id.tv_playNum);
            mTvName = itemView.findViewById(R.id.tv_playlist_name_sm);
        }
    }
}
