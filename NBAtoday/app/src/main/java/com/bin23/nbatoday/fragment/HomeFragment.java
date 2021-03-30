//package com.bin23.nbatoday.fragment;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.bin23.nbatoday.R;
//import com.bin23.nbatoday.adapter.NewsListAdapter;
//import com.bin23.nbatoday.entity.NewsBean;
//import com.bin23.nbatoday.utils.NewsUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class HomeFragment extends Fragment {
//
//    private ListView showList;
//    // ListView内部数据源
//    private List<NewsBean> mNewsDatas;
////
//    private List<NewsBean> allNewsList;
//    private NewsListAdapter adapter;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.tab_home_content, container, false);
//        showList = view.findViewById(R.id.home_news_infolist_lv);
//        // 找到ListView对应的数据源
//        mNewsDatas = new ArrayList<>();
//        allNewsList = NewsUtil.getAllNews();
//        mNewsDatas.addAll(allNewsList);
//        // 创建适配器 BaseAdapter 的子类
//        adapter = new NewsListAdapter(view.getContext(), mNewsDatas);
//        // 设置适配器
//        showList.setAdapter(adapter);
//        initUI();
//        return view;
//    }
//
//    private void initUI() {
//        showList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(allNewsList.get(position).getNewsUrl()));
//                startActivity(intent);
//            }
//        });
//    }
//}
