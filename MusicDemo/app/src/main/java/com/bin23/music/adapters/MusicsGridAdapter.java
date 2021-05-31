package com.bin23.music.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bin23.music.R;
import com.bin23.music.activities.AlbumActivity;
import com.bumptech.glide.Glide;

public class MusicsGridAdapter extends RecyclerView.Adapter<MusicsGridAdapter.ViewHolder> {

    private Context mContext;

    public MusicsGridAdapter(Context context) {
        mContext = context;
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
        Glide.with(mContext)
                .load("https://th.bing.com/th/id/Ra1ad377e593da8811d0a417628a1a789?rik=r9Eelwbi19NwTA&riu=http%3a%2f%2fp4.music.126.net%2fmzMD4FTkPStfqefkoiy3Eg%3d%3d%2f3222668581393466.jpg&ehk=OR3Eq4mc0ZRtJOVOGpNMqUsBxHTTpjKQ5N%2fFYLPs1c4%3d&risl=&pid=ImgRaw")
                .into(holder.ivPlaylistCoverIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AlbumActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 99;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPlaylistCoverIcon;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivPlaylistCoverIcon = (ImageView)itemView.findViewById(R.id.iv_playlist_cover_icon);
        }
    }
}
