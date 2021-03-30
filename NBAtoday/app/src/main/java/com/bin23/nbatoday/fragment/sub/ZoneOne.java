package com.bin23.nbatoday.fragment.sub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bin23.nbatoday.R;
import com.bin23.nbatoday.adapter.TeamAdapter;
import com.bin23.nbatoday.entity.TeamBean;
import com.bin23.nbatoday.utils.TeamUtil;

import java.util.List;

public class ZoneOne extends Fragment {
    private GridView gv;
    private List<TeamBean> mDatas;
    private TeamAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zone_view_1, container, false);
        gv = view.findViewById(R.id.zone_grid_list_gv);

        // 获取球队数据源
        mDatas = TeamUtil.getAllTeam();
        // 创建适配器对象
        adapter = new TeamAdapter(view.getContext(), mDatas);
        // 设置适配器
        gv.setAdapter(adapter);

        return view;
    }
}
