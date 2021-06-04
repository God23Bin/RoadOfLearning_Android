package com.bin23.music.activities;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bin23.music.R;
import com.bin23.music.activities.BaseActivity;
import com.bin23.music.adapters.MusicsGridAdapter;
import com.bin23.music.helps.RealmHelper;
import com.bin23.music.helps.UserHelper;
import com.bin23.music.model.MusicSourceModel;
import com.bin23.music.utils.UserUtils;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    private ActionBar actionBar;

    private RecyclerView mRvGrid;
    private MusicsGridAdapter mMgAdapter;

    private SwipeRefreshLayout swipeRefresh;

    private RealmHelper mRealmHelper;
    private MusicSourceModel mMusicSourceModel;

    /**
     * 简化findViewById
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T fd(@IdRes int id) {
        return findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        setHomeAsUp();

        setNavSideBar();
        setMusicsGrid();
    }

    private void initData() {
        mRealmHelper = new RealmHelper();
        mMusicSourceModel = mRealmHelper.getMusicSource();
    }

    private void initView() {
        // 设置、初始化Toolbar
        toolbar = fd(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 滑动布局
        mDrawerLayout = fd(R.id.drawer_layout);
        // 侧边导航栏 NavigationView
        navView = fd(R.id.nav_view);
        // View headerView = navView.getHeaderView(0);
        // TextView tv_username = headerView.findViewById(R.id.username);
        // tv_username.setText(UserHelper.getInstance().getUsername());
        // 网格歌单 RecyclerView
        mRvGrid = fd(R.id.rv_grid);

        swipeRefresh = fd(R.id.swipe_refresh);
        setSwipeRefresh();
    }

    /**
     * 设置HomeAsUp按钮
     */
    private void setHomeAsUp() {
        // 获取 ActionBar ，最左侧的按钮叫 HomeAsUp 按钮
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            // 显示导航按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 设置导航按钮图标
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
    }

    /**
     * 设置NavigationView，处理点击事件
     */
    private void setNavSideBar() {
        // 将call选项默认选中
        navView.setCheckedItem(R.id.nav_msg);
        // 使图片显示原色，不然就是灰色了
        navView.setItemIconTintList(null);
        // 处理NavigationView点击事件
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_msg:
                        break;
                    case R.id.nav_setting:
                        break;
                    case R.id.nav_about:
                        break;
                    case R.id.nav_logout:
                        UserUtils.logout(MainActivity.this);
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * 设置主页的主要部分-歌单的数据显示
     */
    private void setMusicsGrid() {
        // 设置同一行，3个元素，即显示3个歌单
        mRvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        // mMgAdapter = new MusicsGridAdapter(this);
        mMgAdapter = new MusicsGridAdapter(this, mMusicSourceModel.getAlbum());
        // 传入 adapter 显示数据
        mRvGrid.setAdapter(mMgAdapter);
    }

    /**
     * 设置刷新圈圈颜色、刷新逻辑
     */
    private void setSwipeRefresh() {
//        swipeRefresh.setColorSchemeColors(R.color.mainColorH);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 处理刷新逻辑，这里更换下歌单封面
                reFreshPlayList();
            }
        });
    }

    private void reFreshPlayList() {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // HomeAsUp的id永远都是 android.R.id.home
            case android.R.id.home:
                // 调用 DrawerLayout 的 openDrawer() 展示滑动菜单
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    public void onUsernameOrIconClick(View view) {
        Intent intent = new Intent(this, MeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealmHelper.close();
    }
}