package com.bin23.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d("第一个Activity",this.toString());
        Log.d("第一个Activity","Task 的 id 为" + getTaskId());
        setContentView(R.layout.first_layout);
        Button button = (Button)findViewById(R.id.button1);
        /**
         * 点击事件测试Toast
         */
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(FirstActivity.this,"你点击了第一个按钮了哦~",Toast.LENGTH_SHORT).show();
//            }
//        });

        /**
         * 显式Intent
         */
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);
//            }
//        });

        /**
         * Standard 启动模式
         */
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(FirstActivity.this, FirstActivity.class);
//                startActivity(intent);
//            }
//        });

        /**
         * SingleTask 启动模式
         */
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);
//            }
//        });

        /**
         * SingleInstance 启动模式
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 隐式Intent
         */
        Button button_hide = (Button)findViewById(R.id.button_hide);
        button_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.bin23.activitytest.ACTION_START");
//                intent.addCategory("com.bin23.activitytest.MY_CATEGORY");
                startActivity(intent);
            }
        });

        /**
         * 更多隐式用法
         * 启动其他程序的 Activity
         * 比如访问某个网页，调用系统的浏览器来访问
         *
         * 使用系统内置动作 Intent.ACTION_VIEW
         *
         */
        Button button_baidu = (Button)findViewById(R.id.button_baidu);
        button_baidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.baidu.com"));
                startActivity(intent);
            }
        });

        /**
         * 调用手机拨号
         * 使用系统内置动作 Intent.ACTION_DIAL
         */
        Button button_dial = (Button)findViewById(R.id.button_dial);
        button_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        /**
         * 传送数据到下一个 Activity
         * 使用 intent 对象的 putExtra()方法，把数据放在 intent 中
         * 然后到下一个 Activity 后从 intent 中拿出来
         */
//        Button button_sendMsg = (Button)findViewById(R.id.button_sendMsg);
//        button_sendMsg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                intent.putExtra("extra_data","哈喽，你好帅啊！");
//                startActivity(intent);
//            }
//        });

        Button button_sendMsg = (Button)findViewById(R.id.button_sendMsg);
        button_sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("extra_data","哈喽，你好帅啊！");
                //请求码是唯一值即可，这里传 1
                startActivityForResult(intent,1);
            }
        });


    }

    /**
     * 通过MenuInflater的inflate方法，找到我们写的菜单，把它添加到menu对象上
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    /**
     * 定义菜单响应事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //通过item的id来判断点击的是哪个item
        switch (item.getItemId()){
            case R.id.add_item :
                Toast.makeText(this,"已加99999元",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item :
                Toast.makeText(this,"已移除物品",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    /**
     * 接收从 SecondActivity 返回的数据
     * @param requestCode 请求码， FirstActivity设置的，值为1
     * @param resultCode 结果码， 用于判断处理结果是否成功， SecondActivity设置的，值为RESULT_OK
     * @param data 即携带着返回数据的Intent对象
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //通过switch判断该数据来源，因为一个Activity可以调用startActivityForResult()去启动很多Activity
        //每个Activity返回数据都会调用此方法，即onActivityResult，所以需要判断
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("从 SecondActivity 返回的数据：--", data_return);
                }
                break;
            default:
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("第一个Activity","onRestart");
    }
}