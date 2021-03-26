package com.bin23.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.bin23.recyclerviewtest.adapter.FruitAdapter;
import com.bin23.recyclerviewtest.entity.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);
    }

    private void initFruits(){
        // 循环两次充满屏幕
        for (int i = 0; i < 2; i++) {
            fruitList.add(new Fruit("Apple", R.drawable.apple_pic));
            fruitList.add(new Fruit("Banana", R.drawable.banana_pic));
            fruitList.add(new Fruit("Cherry", R.drawable.cherry_pic));
            fruitList.add(new Fruit("Grape", R.drawable.grape_pic));
            fruitList.add(new Fruit("Mango", R.drawable.mango_pic));
            fruitList.add(new Fruit("Orange", R.drawable.orange_pic));
            fruitList.add(new Fruit("Pear", R.drawable.pear_pic));
            fruitList.add(new Fruit("Pineapple", R.drawable.pineapple_pic));
            fruitList.add(new Fruit("Strawberry", R.drawable.strawberry_pic));
            fruitList.add(new Fruit("Watermelon", R.drawable.watermelon_pic));
        }
    }

}