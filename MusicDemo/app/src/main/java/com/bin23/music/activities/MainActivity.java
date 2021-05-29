package com.bin23.music.activities;

import androidx.annotation.IdRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import com.bin23.music.utils.UserUtils;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView  navView;
    private ActionBar actionBar;

    /**
     * 简化findViewById
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

        initView();
        setHomeAsUp();

        setNavSideBar();
    }

    private void initView() {
        // 设置、初始化Toolbar
        toolbar = fd(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 滑动布局
        mDrawerLayout = fd(R.id.drawer_layout);
        // 侧边导航栏 NavigationView
        navView = fd(R.id.nav_view);
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
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    public void onUsernameOrIconClick(View view) {
        Intent intent = new Intent(this, MeActivity.class);
        startActivity(intent);
    }
}