package com.bin23.gsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bin23.gsontest.entity.Person;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.get_json);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonContent = "{\"name\": \"Tom\", \"age\": 20}";
                Person person = new Gson().fromJson(jsonContent, Person.class);

                Log.d("MainActivity", "Person的name为" + person.getName());
                Log.d("MainActivity", "Person的age为" + person.getAge());
            }
        });


    }
}