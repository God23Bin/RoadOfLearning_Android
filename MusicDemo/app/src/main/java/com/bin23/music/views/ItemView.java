package com.bin23.music.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bin23.music.R;

/**
 * 1. left_icon
 *      isShowLeftIcon
 * 2. left_text
 * 3. right_text
 * 4. right_icon
 *      isShowRightArrow
 */
public class ItemView extends FrameLayout {
    // 属性
    private int leftIcon, rightIcon;
    private String leftText, rightText;
    private boolean isShowLeftIcon, isShowRightArrow;

    // 控件
    private View mView;
    private ImageView mLeftIcon, mRightArrowIcon;
    private TextView mTvLeftText, mTvRightText;

    public ItemView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public ItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) return;
        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.itemView);

        leftIcon = typedArray.getResourceId(R.styleable.itemView_left_icon, R.mipmap.logo);
        isShowLeftIcon = typedArray.getBoolean(R.styleable.itemView_show_left_icon, true);      //得到是否显示左侧图标属性标识
        leftText = typedArray.getString(R.styleable.itemView_left_text);
        rightText = typedArray.getString(R.styleable.itemView_right_text);
        rightIcon = typedArray.getResourceId(R.styleable.itemView_right_icon, R.mipmap.item_in);
        isShowRightArrow = typedArray.getBoolean(R.styleable.itemView_show_right_arrow, true);  //得到是否显示右侧图标属性标识
        typedArray.recycle();


        // 绑定layout布局
        mView = LayoutInflater.from(context).inflate(R.layout.item_view,this,false);
        mLeftIcon = mView.findViewById(R.id.iv_left_icon);
        mTvLeftText = mView.findViewById(R.id.tv_left_text);
        mTvRightText = mView.findViewById(R.id.tv_right_text);
        mRightArrowIcon = mView.findViewById(R.id.iv_right_icon);

        // 布局 关联 -》属性
        mLeftIcon.setImageResource(leftIcon);           // 设置左侧图标
        mLeftIcon.setVisibility(isShowLeftIcon ? View.VISIBLE : View.INVISIBLE);            //设置左侧箭头图标是否显示 rightDesc.setText(ta.getString(R.styleable.ItemView_right_text));//设置右侧文字描述
        mTvLeftText.setText(leftText);                  // 设置左侧文字
        mTvRightText.setText(rightText);                // 设置右侧文字
        mRightArrowIcon.setImageResource(rightIcon);    // 设置右侧图标
        mRightArrowIcon.setVisibility(isShowRightArrow ? View.VISIBLE : View.INVISIBLE);    //设置右侧箭头图标是否显示

        // item_view 和 ItemView 绑定起来
        addView(mView);
    }
}
