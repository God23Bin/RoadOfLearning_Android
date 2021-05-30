package com.bin23.music.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bin23.music.R;

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

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            LinearLayout linearLayout = (LinearLayout)itemView;
        }
    }
}
