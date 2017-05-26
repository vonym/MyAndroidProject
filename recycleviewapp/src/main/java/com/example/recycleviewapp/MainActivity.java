package com.example.recycleviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        /*LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);*/
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL);
        RecyclerAdapter adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    //初始化list
    private void initList() {
        list = new ArrayList<>();

    }
}
