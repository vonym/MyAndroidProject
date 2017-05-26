package com.example.service;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.TextureView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView number_Tv, body_Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        number_Tv = (TextView) findViewById(R.id.number_tv);
        body_Tv = (TextView) findViewById(R.id.body_tv);

        Intent intent = getIntent();
        if (intent != null) {
            String number = intent.getStringExtra("number");
            String body = intent.getStringExtra("body");
            number_Tv.setText(number);
            body_Tv.setText(body);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.second, menu);
        return true;
    }

}
