package com.bin23.uitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout_activity);
        Button button = (Button)findViewById(R.id.button_toRL);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_toRL:
                Intent intent = new Intent(AboutLayoutActivity.this, AboutRelativeLayoutActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}