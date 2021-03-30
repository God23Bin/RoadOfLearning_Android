package com.bin23.healthydiet.foodgrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.bin23.healthydiet.R;
import com.bin23.healthydiet.adapter.FoodGridAdapter;
import com.bin23.healthydiet.bean.FoodBean;
import com.bin23.healthydiet.utils.FoodUtils;

import java.util.List;

public class FoodGridActivity extends AppCompatActivity {
    private GridView gv;
    private List<FoodBean> mDatas;

    private FoodGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_grid);

        gv = findViewById(R.id.food_grid_gv);
        // 数据源
        mDatas = FoodUtils.getAllFoodList();
        // 创建适配器对象
        adapter = new FoodGridAdapter(this, mDatas);
        // 设置适配器
        gv.setAdapter(adapter);



    }
}