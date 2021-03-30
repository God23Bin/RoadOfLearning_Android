package com.bin23.nbatoday.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bin23.nbatoday.fragment.MyZoneFragment;
import com.bin23.nbatoday.fragment.sub.ZoneOne;
import com.bin23.nbatoday.fragment.sub.ZoneTwo;

import java.util.List;

public class ZoneFragmentAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 2;
    private List<View> viewLists;
    private List<String> titleLists;
    private ZoneOne zoneOneFragment = null;
    private ZoneTwo zoneTwoFragment = null;

    public ZoneFragmentAdapter(FragmentManager fm) {
        super(fm);
        zoneOneFragment = new ZoneOne();
        zoneTwoFragment = new ZoneTwo();
    }

    public ZoneFragmentAdapter(FragmentManager fm, List<String> titleLists) {
        super(fm);
        zoneOneFragment = new ZoneOne();
        zoneTwoFragment = new ZoneTwo();
//        this.viewLists = viewLists;
        this.titleLists = titleLists;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case MyZoneFragment.PAGE_ONE:
                fragment = zoneOneFragment;
                break;
            case MyZoneFragment.PAGE_TWO:
                fragment = zoneTwoFragment;
                break;
        }
        return fragment;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return super.isViewFromObject(view, object);
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleLists.get(position);
    }
}
