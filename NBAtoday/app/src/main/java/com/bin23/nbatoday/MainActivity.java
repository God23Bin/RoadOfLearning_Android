package com.bin23.nbatoday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bin23.nbatoday.fragment.CommunityFragment;
import com.bin23.nbatoday.fragment.GameFragment;
//import com.bin23.nbatoday.fragment.HomeFragment;
import com.bin23.nbatoday.fragment.HomeNewsFragment;
import com.bin23.nbatoday.fragment.MyZoneFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout tabHome;
    private LinearLayout tabGame;
    private LinearLayout tabCommunity;
    private LinearLayout tabMyZone;

    private ImageButton ibHome;
    private ImageButton ibGame;
    private ImageButton ibCommunity;
    private ImageButton ibMyZone;

    private Fragment homeFragment;
    private Fragment gameFragment;
    private Fragment communityFragment;
    private Fragment myZoneFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化控件
        initEvent();//初始化事件
        setSelect(0);//对事物方法调用显示第一个界面
    }

    private void initView() {
        tabHome = findViewById(R.id.id_tab_home);
        tabGame = findViewById(R.id.id_tab_game);
        tabCommunity  = findViewById(R.id.id_tab_community);
        tabMyZone  = findViewById(R.id.id_tab_myzone);

        ibHome = findViewById(R.id.id_tab_home_img);
        ibGame = findViewById(R.id.id_tab_game_img);
        ibCommunity  = findViewById(R.id.id_tab_community_img);
        ibMyZone  = findViewById(R.id.id_tab_myzone_img);

    }

    private void initEvent() {
        tabHome.setOnClickListener(this);
        tabGame.setOnClickListener(this);
        tabCommunity.setOnClickListener(this);
        tabMyZone.setOnClickListener(this);
    }


    /**
     * 选择碎片的方法
     * @param i
     */
    private void setSelect(int i) {

        // FragmentManager fm = getFragmentManager();  //先拿到管理器
        FragmentManager fm = getSupportFragmentManager();  //使用V4包下的Fragment是的事务管理器
        FragmentTransaction transaction = fm.beginTransaction();  //开启一个事务transaction

        //一开始隐藏所有Fragment
        hideFragment(transaction); //自定义一个函数先对所有事务进行隐藏
        //将图片切换为亮色
        //切换界面
        switch (i) {  //切换图片为亮色
            case 0: {
                if (homeFragment == null) {
                    //为空则初始化他
                    homeFragment = new HomeNewsFragment();
                    transaction.add(R.id.id_container, homeFragment);  //动态添加Fragment到指定的id位置
                } else {
                    //否则对其进行显示
                    transaction.show(homeFragment);
                }
//                ibHome.setImageResource(R.drawable.tab_home_normal); //切换图片
                break;
            }
            case 1: {
                if (gameFragment == null) {
                    //为空则初始化他
                    gameFragment = new GameFragment();
                    transaction.add(R.id.id_container, gameFragment);  //初始化Fragment
                } else {
                    //否则对其进行显示
                    transaction.show(gameFragment);
                }
//                ibGame.setImageResource(R.drawable.tab_game_normal);
                break;
            }
            case 2: {
                if (communityFragment == null) {
                    //为空则初始化他
                    communityFragment = new CommunityFragment();
                    transaction.add(R.id.id_container, communityFragment);  //初始化Fragment
                } else {
                    //否则对其进行显示
                    transaction.show(communityFragment);
                }
//                ibCommunity.setImageResource(R.drawable.tab_community_normal);
                break;
            }
            case 3: {
                if (myZoneFragment == null) {
                    //为空则初始化他
                    myZoneFragment = new MyZoneFragment();
                    transaction.add(R.id.id_container, myZoneFragment);  //初始化Fragment
                } else {
                    //否则对其进行显示
                    transaction.show(myZoneFragment);
                }
//                ibMyZone.setImageResource(R.drawable.tab_myzone_normal);
                break;
            }

        }//switch

        transaction.commit();//提交事务
    }//setSelect()


    //隐藏事务方法
    private void hideFragment(FragmentTransaction transaction) {  //对不为空的Fragment隐藏
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (gameFragment != null) {
            transaction.hide(gameFragment);
        }
        if (communityFragment != null) {
            transaction.hide(communityFragment);
        }
        if (myZoneFragment != null) {
            transaction.hide(myZoneFragment);
        }
    }

    //图片设置为暗色
    private void resetImg() {
        ibHome.setImageResource(R.drawable.tab_home_normal);
        ibGame.setImageResource(R.drawable.tab_game_normal);
        ibCommunity.setImageResource(R.drawable.tab_community_normal);
        ibMyZone.setImageResource(R.drawable.tab_myzone_normal);
    }

    @Override
    public void onClick(View view) {

        resetImg();//设置暗色
        switch (view.getId()) {
            case R.id.id_tab_home: {
                setSelect(0);
                ibHome.setImageResource(R.drawable.tab_home_pressed);  //将点击的图标设置为亮色
                break;
            }
            case R.id.id_tab_game: {
                setSelect(1);
                ibGame.setImageResource(R.drawable.tab_game_pressed);
                break;
            }
            case R.id.id_tab_community: {
                setSelect(2);
                ibCommunity.setImageResource(R.drawable.tab_community_pressed);
                break;
            }
            case R.id.id_tab_myzone: {
                setSelect(3);
                ibMyZone.setImageResource(R.drawable.tab_myzone_pressed);
                break;
            }

        }

    }
}