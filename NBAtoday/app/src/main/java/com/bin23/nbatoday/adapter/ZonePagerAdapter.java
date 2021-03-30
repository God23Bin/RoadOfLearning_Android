//package com.bin23.nbatoday.adapter;
//
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.viewpager.widget.PagerAdapter;
//
//import java.util.ArrayList;
//
//public class ZonePagerAdapter extends PagerAdapter {
//
//    private ArrayList<View> viewLists;
//    private ArrayList<String> titleLists;
//
//    public ZonePagerAdapter() {
//    }
//
//    public ZonePagerAdapter(ArrayList<View> viewLists) {
//        this.viewLists = viewLists;
//    }
//
//    public ZonePagerAdapter(ArrayList<View> viewLists, ArrayList<String> titleLists) {
//        this.viewLists = viewLists;
//        this.titleLists = titleLists;
//    }
//
//    @Override
//    public int getCount() {
//        return viewLists.size();
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        container.addView(viewLists.get(position));
//        return viewLists.get(position);
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView(viewLists.get(position));
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return titleLists.get(position);
//    }
//}
