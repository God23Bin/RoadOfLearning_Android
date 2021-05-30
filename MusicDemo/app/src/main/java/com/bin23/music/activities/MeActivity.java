package com.bin23.music.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bin23.music.R;
import com.bin23.music.interfaces.MyCallBack;
import com.bin23.music.utils.RegionalChooseUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static java.security.AccessController.getContext;

public class MeActivity extends BaseActivity {

    private ImageView mHeadBack, mHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    private void initView() {
        initToolBar(true, "我的资料");

        // 头部图像初始化
        mHeadBack = findViewById(R.id.h_back);
        setHeaderWithGlide();

        // 城市选择器初始化
        RegionalChooseUtil.initJsonData(MeActivity.this);
    }

    public void setHeaderWithGlide() {
        //设置背景磨砂效果
        Glide.with(this).load(R.mipmap.nav_icon)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(14, 3)))
                .transition(DrawableTransitionOptions.withCrossFade())  //淡入淡出
                .into(mHeadBack);

    }

    public void onNicknameItemClick(View view) {
        new XPopup.Builder(MeActivity.this).asInputConfirm("修改昵称", "",
                new OnInputConfirmListener() {
                    @Override
                    public void onConfirm(String text) {
                        // 进行数据库操作
                        Toast.makeText(MeActivity.this, "你点击了确定" + text, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void onSexItemClick(View view) {
        new XPopup.Builder(MeActivity.this).asInputConfirm("修改性别", "",
                new OnInputConfirmListener() {
                    @Override
                    public void onConfirm(String text) {
                        // 进行数据库操作
                        Toast.makeText(MeActivity.this, "你点击了确定" + text, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /**
     * 城市选择点击事件
     * @param view
     */
    public void onAreaItemClick(View view) {
        // 弹窗显示
        RegionalChooseUtil.showPickerView(MeActivity.this, new MyCallBack() {
            @Override
            public void callBack(Object object) {
                // object 获取到城市选择结果
                Toast.makeText(MeActivity.this, object.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onSignItemClick(View view) {
        new XPopup.Builder(MeActivity.this).asInputConfirm("修改简介", "",
                new OnInputConfirmListener() {
                    @Override
                    public void onConfirm(String text) {
                        // 进行数据库操作
                        Toast.makeText(MeActivity.this, "你点击了确定" + text, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void onUpdatePwItemClick(View view) {
        Intent intent = new Intent(MeActivity.this, UpdatePwActivity.class);
        startActivity(intent);
    }

    public void onVersionItemClick(View view) {
        Toast.makeText(MeActivity.this, "当前版本为1.0", Toast.LENGTH_SHORT).show();
    }
}