package com.bin23.nbatoday.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bin23.nbatoday.R;
import com.bin23.nbatoday.adapter.ZoneFragmentAdapter;
//import com.bin23.nbatoday.adapter.ZonePagerAdapter;

import java.util.ArrayList;

public class MyZoneFragment extends Fragment implements ViewPager.OnPageChangeListener{

    private ViewPager myZonePg;
    private ArrayList<View> viewList;
    private ArrayList<String> titleList;
//    private ZonePagerAdapter zonePagerAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;

    private ZoneFragmentAdapter zoneFragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_myzone_content, container, false);
        myZonePg = view.findViewById(R.id.myzone_pg);
//        viewList = new ArrayList<View>();
//        LayoutInflater li = getLayoutInflater();
//        viewList.add(li.inflate(R.layout.zone_view_1,null,false));
//        viewList.add(li.inflate(R.layout.zone_view_2,null,false));
        titleList = new ArrayList<String>();
        titleList.add("NBA中心");
        titleList.add("登录");
//        zonePagerAdapter = new ZonePagerAdapter(viewList, titleList);
        zoneFragmentAdapter = new ZoneFragmentAdapter(getChildFragmentManager(), titleList);
//        myZonePg.setAdapter(zonePagerAdapter);
        myZonePg.setAdapter(zoneFragmentAdapter);


        return view;
    }

//    private void bindViews(View view) {
//        myZonePg = view.findViewById(R.id.myzone_pg);
////        myZonePg.setAdapter(zoneFragmentAdapter);
//        myZonePg.setCurrentItem(0);
//        myZonePg.addOnPageChangeListener(this);
//    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
