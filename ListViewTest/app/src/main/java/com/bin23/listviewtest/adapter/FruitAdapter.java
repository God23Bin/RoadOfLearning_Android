package com.bin23.listviewtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bin23.listviewtest.R;
import com.bin23.listviewtest.entity.Fruit;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private Integer resourceId;

    /**
     * 这个构造方法，用于将Activity的实例、ListView子项布局的id和数据源传递进来
     * @param context
     * @param textViewResourceId
     * @param objects
     */
    public FruitAdapter(Context context, Integer textViewResourceId, List<Fruit> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    /**
     * 重写getView()方法，该方法在每个子项被滚动到屏幕内时会被调用
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // 获取当前的Fruit对象
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            // 通过LayoutInflater的from()方法创建LayoutInflater对象，然后调用inflate()方法动态加载一个布局文件
            // 参数（1.id：要加载的布局文件的id，
            //      2.父布局：给加载好的布局再添加一个父布局，
            //      3.基本写false，表示只让父布局中声明的layout属性生效，不会添加父布局）
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruitImage);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruitName);
            // 将 ViewHolder 存储到 View 中
            view.setTag(viewHolder);
        } else {
            view = convertView;
            //重新获取 ViewHolder
            viewHolder = (ViewHolder)view.getTag();
        }
        // 无需每次都调用 findViewById()
//        ImageView fruitImage = (ImageView)view.findViewById(R.id.fruitImage);
//        TextView fruitName = (TextView)view.findViewById(R.id.fruitName);
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
    }
}
