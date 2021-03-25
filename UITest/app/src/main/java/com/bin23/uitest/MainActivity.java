package com.bin23.uitest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button :
                editText = (EditText)findViewById(R.id.editText);
                String text = editText.getText().toString();
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2 :
                imageView = (ImageView)findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.img_2);
                break;
            case R.id.button3 :
                progressBar = (ProgressBar)findViewById(R.id.progressBar);
//                if(progressBar.getVisibility() == View.VISIBLE){
//                    progressBar.setVisibility(View.GONE);
//                }else{
//                    progressBar.setVisibility(View.VISIBLE);
//                }
                progressBar.setProgress(progressBar.getProgress() + 10);
                break;
            case R.id.button4 :
                AlertDialog.Builder builderDialog = new AlertDialog.Builder(MainActivity.this);
                builderDialog.setTitle("这是Dialog");
                builderDialog.setMessage("这很重要的，确认删除？");
                builderDialog.setCancelable(false);
                builderDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builderDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builderDialog.show();
                break;
            case R.id.button5 :
                Intent intent = new Intent(MainActivity.this, AboutLayoutActivity.class);
                startActivity(intent);
                break;
            default:break;
        }
    }
}