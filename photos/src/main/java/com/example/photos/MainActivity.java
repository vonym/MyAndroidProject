package com.example.photos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLl_1, mLl_2, mLl_3, mLl_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setOnClick();
    }

    private void setOnClick() {
        mLl_1.setOnClickListener(MainActivity.this);
        mLl_2.setOnClickListener(MainActivity.this);
        mLl_3.setOnClickListener(MainActivity.this);
        mLl_4.setOnClickListener(MainActivity.this);
    }

    private void init() {
        mLl_1 = (LinearLayout) findViewById(R.id.main_ll_1);
        mLl_2 = (LinearLayout) findViewById(R.id.main_ll_2);
        mLl_3 = (LinearLayout) findViewById(R.id.main_ll_3);
        mLl_4 = (LinearLayout) findViewById(R.id.main_ll_4);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setIcon(R.mipmap.ic_launcher).setTitle("Photos").setMessage("确定退出吗？").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, ItemActivity.class);
        int flag = 0;
        switch (v.getId()) {
            case R.id.main_ll_1:
                flag = 1;
                break;
            case R.id.main_ll_2:
                flag = 2;
                break;
            case R.id.main_ll_3:
                flag = 3;
                break;
            case R.id.main_ll_4:
                flag = 4;
                break;
        }
        intent.putExtra("flag", flag);
        startActivity(intent);
    }
}
