package com.bin23.music.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class WEqualHeightImageView extends AppCompatImageView {
    public WEqualHeightImageView(@NonNull Context context) {
        super(context);
    }

    public WEqualHeightImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualHeightImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 下面两个变量是 ImageView的宽度、长度与模式的混合值
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        // Mode 指 - match_parent / wrap_content / 具体的dp这些
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    }
}
