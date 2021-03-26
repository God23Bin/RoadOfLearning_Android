package com.bin23.recyclerviewtest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bin23.recyclerviewtest.R;
import com.bin23.recyclerviewtest.entity.Fruit;

import java.util.List;

public class FruitAdapterGundong extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> fruitList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;

        /**
         *
         * @param view 通常就是 RecyclerView 的子项布局
         */
        public ViewHolder(View view){
            super(view);
            fruitImage = (ImageView)view.findViewById(R.id.fruitImage);
            fruitName = (TextView)view.findViewById(R.id.fruitName);
        }
    }

    /**
     * 主要就是把数据源传进这个适配器
     * @param fruitList
     */
    public FruitAdapterGundong(List<Fruit> fruitList) {
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
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item_gundong, parent, false);
        FruitAdapter.ViewHolder holder = new FruitAdapter.ViewHolder(view);
        return holder;
    }

    /**
     * 对 RecyclerView 子项的数据进行赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.ViewHolder holder, int position) {
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
