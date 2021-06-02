package com.bin23.music.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import com.bin23.music.R;

public class BaseActivity extends Activity {

    private ImageView mIvBack;
    private TextView mTvTitle;

    /**
     * 简化findViewById
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T fd(@IdRes int id) {
        return findViewById(id);
    }


    protected void initToolBar (boolean isShowBack, String title) {
        mIvBack = fd(R.id.iv_back);
        mTvTitle = fd(R.id.tv_toolbar_title);

        mIvBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        mTvTitle.setText(title);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                // 解决动画错误
//                ((Activity)BaseActivity.this).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
//                ((Activity)BaseActivity.this).overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
            }
        });
    }

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
