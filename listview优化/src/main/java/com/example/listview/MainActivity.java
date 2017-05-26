package com.example.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

    /*ListView的优化
    * Q1.为什么要优化
    * 答：如果一个ListView中的数据很多，并且每一个item都需要很复杂的布局，那么这时候滑动ListView就会很卡顿
    * Q2.优化的原理
    * 答：
    * Q3.如果我们要进一步定制ListView，该怎么做
    * 答：*/
public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        list = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            list.add(i);
        }
        listView.setAdapter(new MyAdapter(this, list));
    }
}
