package com.bin23.activitytest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("第三个Activity","Task 的 id 为" + getTaskId());
        setContentView(R.layout.third_layout);

        Button button_finishAll = (Button)findViewById(R.id.button_finishAll);
        button_finishAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCollector.finishAll();
            }
        });
    }
}