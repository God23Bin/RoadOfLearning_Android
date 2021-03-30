package com.bin23.healthydiet.foodlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bin23.healthydiet.R;
import com.bin23.healthydiet.adapter.InfoListAdapter;
import com.bin23.healthydiet.bean.FoodBean;
import com.bin23.healthydiet.utils.FoodUtils;

import java.util.ArrayList;
import java.util.List;

public class InfoListActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText searchEt;
    private ImageView searchIv, flushIv;
    private ListView showLv;
    // ListView内部数据源
    private List<FoodBean> mDatas;

    private List<FoodBean> allFoodList;

    private InfoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        // 查找控件
        initView();
        // 找到ListView对应的数据源
        mDatas = new ArrayList<>();
        allFoodList = FoodUtils.getAllFoodList();
        mDatas.addAll(allFoodList);
        // 创建适配器 BaseAdapter 的子类
        adapter = new InfoListAdapter(this, mDatas);
        // 设置适配器
        showLv.setAdapter(adapter);
    }

    private void initView() {
        searchEt = findViewById(R.id.info_et_search);
        searchIv = findViewById(R.id.info_iv_search);
        flushIv = findViewById(R.id.info_iv_flush);
        showLv = findViewById(R.id.infolist_lv);
        searchIv = findViewById(R.id.info_iv_search);
        flushIv = findViewById(R.id.info_iv_flush);
        // 添加点击事件监听器
        searchIv.setOnClickListener(this);
        flushIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_iv_search : //搜索点击
                // 获取输入内容，判断不为空
                String msg = searchEt.getText().toString().trim();
                if (TextUtils.isEmpty(msg)) {
                    Toast.makeText(this, "输入内容不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 判断所有食物列表的标题是否包含输入的内容，如果包含那么将其添加到小的List集合中
                List<FoodBean> list = new ArrayList<>();
                for (int i = 0; i < allFoodList.size(); i++) {
                    String title = allFoodList.get(i).getTitle();
                    if (title.contains(msg)) {
                        list.add(allFoodList.get(i));
                    }
                }
                mDatas.clear();     // 清空ListView适配器中数据的内容
                mDatas.addAll(list);// 添加新的数据到数据源中
                adapter.notifyDataSetChanged(); //提示适配器更新
                break;
            case R.id.info_iv_flush : //刷新点击
                searchEt.setText("");
                mDatas.clear();
                mDatas.addAll(allFoodList);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}