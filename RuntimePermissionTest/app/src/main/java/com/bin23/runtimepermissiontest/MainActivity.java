package com.bin23.runtimepermissiontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button makeCall = (Button) findViewById(R.id.make_call);
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断用户是否已经给我们授权了
                // 使用ContextCompat.checkSelfPermission()方法
                // 将返回值与PackageManager.PERMISSION_GRANTED作比较，相等就代表授权了
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
                    // 没有授权，调用ActivityCompat.requestPermissions()方法向用户申请权限
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
                    // 不管拒绝与否，都会回调到onRequestPermissionsResult()方法中，授权结果封装在grantResults中
                } else {
                    call();
                }
//                try {
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    intent.setData(Uri.parse("tel:10086"));
//                    startActivity(intent);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "你拒绝了允许请求", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}