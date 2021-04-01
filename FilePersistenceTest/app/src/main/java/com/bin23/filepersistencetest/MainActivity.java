package com.bin23.filepersistencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.edit);
        // 读取退出前保存的数据
        String inputText = load();
        // 如果 inputText 不为空，则设置到editText中，光标定位到最后一位
        if(!TextUtils.isEmpty(inputText)){
            editText.setText(inputText);
            editText.setSelection(inputText.length());
            Toast.makeText(this, "读取成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText = editText.getText().toString();
        save(inputText);
    }

    /**
     * 数据存储 - 文件存储方式
     * 获取输出流，输出文件名为data的文件，模式是默认模式
     * 然后从输出流获取Writer，把inputText写出去
     * @param inputText
     */
    public void save(String inputText){
        FileOutputStream dataOut = null;
        BufferedWriter bufferedWriter = null;
        try {
            dataOut = openFileOutput("data", Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(dataOut));
            bufferedWriter.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedWriter!=null){
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 数据读取
     * 使用openFileInput获取文件输入流
     * 通过输入流获取Reader
     * 开始读
     * @return 读取的数据
     */
    public String load(){
        FileInputStream dataIn = null;
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();
        try {
            dataIn = openFileInput("data");
            bufferedReader = new BufferedReader(new InputStreamReader(dataIn));
            String line = "";
            while((line = bufferedReader.readLine())!=null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedReader!=null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content.toString();
    }
}