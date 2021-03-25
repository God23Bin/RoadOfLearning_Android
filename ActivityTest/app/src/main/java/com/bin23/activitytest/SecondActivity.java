package com.bin23.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("第二个Activity","Task 的 id 为" + getTaskId());
        setContentView(R.layout.second_layout);
        final Intent intent = getIntent();
        String extra_data = intent.getStringExtra("extra_data");
        if(extra_data!=null){
            Log.d("传过来的数据：-----",extra_data);
        }


        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.putExtra("data_return","你好啊，帅帅的 FirstActivity");
                setResult(RESULT_OK,intent1);
                finish();
            }
        });

        Button button_toThird = (Button)findViewById(R.id.button_toThird);
        button_toThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("第二个Activity","onDestroy");
    }
}