package com.bin23.wechatpractice;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bin23.wechatpractice.fragment.AddressFragment;
import com.bin23.wechatpractice.fragment.FriendFragment;
import com.bin23.wechatpractice.fragment.SettingFragment;
import com.bin23.wechatpractice.fragment.WeChatFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout tabWeChat;
    private LinearLayout tabFriend;
    private LinearLayout tabAddress;
    private LinearLayout tabSetting;

    private ImageButton ibWeChat;
    private ImageButton ibFriend;
    private ImageButton ibAddress;
    private ImageButton ibSetting;

    private Fragment wechatFragment;
    private Fragment friendFragment;
    private Fragment addressFragment;
    private Fragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏默认的标题栏
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }
        initView();//初始化控件
        initEvent();//初始化事件
        setSelect(0);//对事物方法调用显示第一个界面
    }

    private void initView() {
        tabWeChat = (LinearLayout) findViewById(R.id.id_tab_wechat);
        tabFriend = (LinearLayout) findViewById(R.id.id_tab_frd);
        tabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        tabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);

        ibWeChat = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        ibFriend = (ImageButton) findViewById(R.id.id_tab_frd_img);
        ibAddress = (ImageButton) findViewById(R.id.id_tab_address_img);
        ibSetting = (ImageButton) findViewById(R.id.id_tab_setting_img);
    }

    private void initEvent() {
        tabWeChat.setOnClickListener(this);
        tabFriend.setOnClickListener(this);
        tabAddress.setOnClickListener(this);
        tabSetting.setOnClickListener(this);
    }


    //自定义一个方法
    private void setSelect(int i) {

        // FragmentManager fm = getFragmentManager();  //先拿到管理器
        FragmentManager fm = getSupportFragmentManager();  //使用V4包下的Fragment是的事务管理器
        FragmentTransaction transaction = fm.beginTransaction();  //开启一个事务transaction

        hideFragment(transaction); //自定义一个函数先对所有事务进行隐藏
        //将图片切换为亮色
        //切换界面
        switch (i) {  //切换图片为亮色
            case 0: {
                if (wechatFragment == null) {
                    //为空则初始化他
                    wechatFragment = new WeChatFragment();
                    transaction.add(R.id.id_container, wechatFragment);  //初始化Fragment
                } else {
                    //否则对其进行显示
                    transaction.show(wechatFragment);
                }
                ibWeChat.setImageResource(R.drawable.tab_weixin_normal); //切换图片
                break;
            }
            case 1: {
                if (friendFragment == null) {
                    //为空则初始化他
                    friendFragment = new FriendFragment();
                    transaction.add(R.id.id_container, friendFragment);  //初始化Fragment
                } else {
                    //否则对其进行显示
                    transaction.show(friendFragment);
                }
                ibFriend.setImageResource(R.drawable.tab_find_frd_normal);
                break;
            }
            case 2: {
                if (addressFragment == null) {
                    //为空则初始化他
                    addressFragment = new AddressFragment();
                    transaction.add(R.id.id_container, addressFragment);  //初始化Fragment
                } else {
                    //否则对其进行显示
                    transaction.show(addressFragment);
                }
                ibAddress.setImageResource(R.drawable.tab_address_normal);
                break;
            }
            case 3: {
                if (settingFragment == null) {
                    //为空则初始化他
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.id_container, settingFragment);  //初始化Fragment
                } else {
                    //否则对其进行显示
                    transaction.show(settingFragment);
                }
                ibSetting.setImageResource(R.drawable.tab_settings_normal);
                break;
            }

        }//switch

        transaction.commit();//提交事务
    }//setSelect()


    //隐藏事务方法
    private void hideFragment(FragmentTransaction transaction) {  //对不为空的Fragment隐藏
        if (wechatFragment != null) {
            transaction.hide(wechatFragment);
        }
        if (friendFragment != null) {
            transaction.hide(friendFragment);
        }
        if (addressFragment != null) {
            transaction.hide(addressFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

    //图片设置为暗色
    private void resetImg() {
        ibWeChat.setImageResource(R.drawable.tab_weixin_normal);
        ibFriend.setImageResource(R.drawable.tab_find_frd_normal);
        ibAddress.setImageResource(R.drawable.tab_address_normal);
        ibSetting.setImageResource(R.drawable.tab_settings_normal);
    }

    @Override
    public void onClick(View view) {

        resetImg();//设置暗色
        switch (view.getId()) {
            case R.id.id_tab_wechat: {
                setSelect(0);
                ibWeChat.setImageResource(R.drawable.tab_weixin_pressed);  //将点击的图标设置为亮色
                break;
            }
            case R.id.id_tab_frd: {
                setSelect(1);
                ibFriend.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            }
            case R.id.id_tab_address: {
                setSelect(2);
                ibAddress.setImageResource(R.drawable.tab_address_pressed);
                break;
            }
            case R.id.id_tab_setting: {
                setSelect(3);
                ibSetting.setImageResource(R.drawable.tab_settings_pressed);
                break;
            }

        }

    }

}