package com.bin23.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bin23.litepaltest.entity.Book;

import org.litepal.tablemanager.Connector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // org.litepal.tablemanager.Connector
                Connector.getDatabase();
            }
        });

        Button addData = (Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("C++");
                book.setAuthor("Bin");
                book.setPages(600);
                book.setPrice(23);
                book.save();
            }
        });
        Button updateData = (Button)findViewById(R.id.update_data);
        Button deleteData = (Button)findViewById(R.id.delete_data);
        Button queryData = (Button)findViewById(R.id.query_data);
    }
}