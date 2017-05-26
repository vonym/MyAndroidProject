package com.example.vonym.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vonym on 16-12-9.
 */
public class NewClient extends Activity {
    private ListView listView;
    private List<Map<String, Object>> list;
    private int[] imgs = {R.mipmap.ic_launcher};
    private String[] titles = {"哈哈哈哈"};
    private String[] docs = {"呵呵呵"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        listView = (ListView) findViewById(R.id.list);
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", imgs[0]);
            map.put("title", titles[0] + (i + 1));
            map.put("doc", docs[0] + (i + 1));
            list.add(map);
        }
        listView.setAdapter(new NewsAdapter(this, list));
    }
}
