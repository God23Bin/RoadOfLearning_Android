package com.bin23.recyclerviewtest.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bin23.recyclerviewtest.MainActivity;
import com.bin23.recyclerviewtest.R;
import com.bin23.recyclerviewtest.SecondActivity;
import com.bin23.recyclerviewtest.entity.Fruit;

import java.util.List;

/**
 * 继承 RecyclerView.Adapter
 * 就需要重写三个方法
 *      1. onCreateViewHolder()
 *      2. onBindViewHolder()
 *      3. getItemCount()
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> fruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        // 点击事件由具体的 View 去实现
        // 用来保存子项最外层布局的实例，然后在onCreateViewHolder()方法中实现点击事件就可以了
        View fruitView;

        /**
         *
         * @param view 通常就是 RecyclerView 的子项布局
         */
        public ViewHolder(View view){
            super(view);
            fruitImage = (ImageView)view.findViewById(R.id.fruitImage);
            fruitName = (TextView)view.findViewById(R.id.fruitName);
            fruitView = view;
        }
    }

    /**
     * 主要就是把数据源传进这个适配器
     * @param fruitList
     */
    public FruitAdapter(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    /**
     * 加载 fruit_item 布局，然后创建 ViewHolder 实例
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户点击的 position
                int adapterPosition = holder.getAdapterPosition();
                Fruit fruit = fruitList.get(adapterPosition);
                Toast.makeText(v.getContext(), "你点击了 view " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                Fruit fruit = fruitList.get(adapterPosition);
                Toast.makeText(v.getContext(), "你点击了 image " + fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    /**
     * 对 RecyclerView 子项的数据进行赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = fruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    /**
     * 告知我们 RecyclerView 的子项有多少个
     * @return
     */
    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
