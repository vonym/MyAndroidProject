package com.example.vonym.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vonym on 16-12-8.
 */
public class SecondMainActivity extends Activity {
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> list;
    private int[] imgs = {R.drawable.pengyouquan, R.drawable.renxingsongdali,
            R.drawable.dianyingpiao, R.drawable.aiqiyijiazu, R.drawable.papaqi};
    private String[] txts = {"朋友圈", "动态", "附近人在看", "任性送大礼",
            "电影票", "爱奇艺商城", "爱奇艺家族", "啪啪奇-手机拍大片"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        for (int i = 0; i < txts.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", imgs[i%imgs.length]);
            map.put("txt", txts[i]);
            list.add(map);
        }
        simpleAdapter = new SimpleAdapter(SecondMainActivity.this, list, R.layout.second_listview, new String[]{"img","txt"}, new int[]{R.id.img,R.id.txt});
        listView.setAdapter(simpleAdapter);
    }
}
