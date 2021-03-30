package com.bin23.uibestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bin23.uibestpractice.adapter.MsgAdapter;
import com.bin23.uibestpractice.entity.Msg;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText inputText;

    private Button send;

    private RecyclerView recyclerView;

    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsgs();
        inputText = findViewById(R.id.inputText);
        send = findViewById(R.id.send);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        msgAdapter = new MsgAdapter(msgList);
        recyclerView.setAdapter(msgAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!content.isEmpty()){
                    Msg msg = new Msg(content, Msg.getTypeSent());
                    msgList.add(msg);
                    // 当有消息时，刷新msgList的显示
                    msgAdapter.notifyItemInserted(msgList.size() - 1);
                    // 将recyclerView定位到最后一行
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    // 清空输入框内容
                    inputText.setText("");
                }

            }
        });
    }

    private void initMsgs(){
        Msg msg1 = new Msg("你好啊，你这个家伙", Msg.getTypeReceived());
        Msg msg2 = new Msg("你好你好，最近怎么样？", Msg.getTypeSent());
        Msg msg3 = new Msg("最近过得还行，我学了安卓的编程开发", Msg.getTypeReceived());
        msgList.add(msg1);
        msgList.add(msg2);
        msgList.add(msg3);
    }
}