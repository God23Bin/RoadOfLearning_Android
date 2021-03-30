package com.bin23.uibestpractice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bin23.uibestpractice.R;
import com.bin23.uibestpractice.entity.Msg;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter {

    private List<Msg> msgList;

    static class LeftViewHolder extends RecyclerView.ViewHolder{
        TextView leftMsg;

        public LeftViewHolder(View view){
            super(view);
            leftMsg = (TextView)view.findViewById(R.id.leftMsg);
        }
    }

    static class RightViewHolder extends RecyclerView.ViewHolder{
        TextView rightMsg;

        public RightViewHolder(View view){
            super(view);
            rightMsg = (TextView)view.findViewById(R.id.rightMsg);
        }
    }

    public MsgAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }

    @Override
    public int getItemViewType(int position) {
        Msg msg = msgList.get(position);
        return msg.getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Msg.getTypeReceived()){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_left_item, parent, false);
            LeftViewHolder viewHolder = new LeftViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_right_item, parent, false);
            RightViewHolder viewHolder = new RightViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        if (holder instanceof LeftViewHolder){
            ((LeftViewHolder) holder).leftMsg.setText(msg.getContent());
        }else if(holder instanceof RightViewHolder){
            ((RightViewHolder) holder).rightMsg.setText(msg.getContent());
        }else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
