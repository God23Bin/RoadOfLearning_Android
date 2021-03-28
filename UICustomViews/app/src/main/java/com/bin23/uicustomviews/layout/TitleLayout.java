package com.bin23.uicustomviews.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bin23.uicustomviews.R;


public class TitleLayout extends LinearLayout {

    public TitleLayout (Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        // LayoutInflater的from构建LayoutInflater对象，然后使用inflate()方法加载布局文件
        LayoutInflater.from(context).inflate(R.layout.title, this);

        Button edit=(Button) findViewById(R.id.title_edit);
        Button back=(Button) findViewById(R.id.title_back);

        edit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getContext(), "Edit", Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getContext(), "Back", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
