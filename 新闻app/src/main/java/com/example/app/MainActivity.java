package com.example.app;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private FloatingActionButton floatBtn;
    private ImageView iv_top, iv_keji;
    private List<ImageView> imgs;
    private String url = "http://v.juhe.cn/toutiao/index?type=top&key=e68c6cd9bda71e5c21361e67da4a0cf9";
    private boolean is_press = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListviewAdapter();
        setListener();
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        listView = (ListView) findViewById(R.id.list);
        floatBtn = (FloatingActionButton) findViewById(R.id.floatbtn);
        iv_keji = (ImageView) findViewById(R.id.iv_keji);
        iv_top = (ImageView) findViewById(R.id.iv_top);
        imgs = new ArrayList<>();
        imgs.add(iv_keji);
        imgs.add(iv_top);

    }

    private void setListviewAdapter() {
        ConnectionUtils.getNewsResource(url, new HttpCallbackListener() {
            @Override
            public void onSuccess(Object jsonData) {
                Gson gson = new Gson();
                final NewsBean newsBean = gson.fromJson((String) jsonData, NewsBean.class);
                final List<NewsBean.ResultBean.DataBean> dataBeanList = newsBean.getResult().getData();
                //Log.v("msg", (String) jsonData);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final ListViewAdapter adapter = new ListViewAdapter(MainActivity.this, dataBeanList);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                intent.putExtra("url", newsBean.getResult().getData().get(position).getUrl());
                                startActivity(intent);
                            }
                        });
                        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(2000);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                /*dataBeanList.clear();
                                                dataBeanList.addAll(newsBean.getResult().getData());*/
                                                adapter.notifyDataSetChanged();
                                                swipeRefreshLayout.setRefreshing(false);
                                            }
                                        });
                                    }
                                }).start();
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Log.v("msg", "错误");
            }
        });
    }

    private void startAnimation() {
        for (int i = 0; i < imgs.size(); i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imgs.get(i), "translationY", -(200f * (i + 1)));
            animator.setDuration(2000);
            animator.start();
        }
        is_press = true;
    }

    private void endAnimation() {
        for (int i = 0; i < imgs.size(); i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imgs.get(i), "translationY", (200f * (i + 1)));
            animator.setDuration(2000);
            animator.start();
        }
        is_press = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setListener() {
        floatBtn.setOnClickListener(this);
        iv_top.setOnClickListener(this);
        iv_keji.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatbtn:
                if (!is_press) {
                    startAnimation();
                } else {
                    endAnimation();
                }
                break;
            case R.id.iv_top:
                Log.v("msg", "top");
                url = "http://v.juhe.cn/toutiao/index?type=top&key=e68c6cd9bda71e5c21361e67da4a0cf9";
                setListviewAdapter();
                endAnimation();
                Log.v("msg", url);
                break;
            case R.id.iv_keji:
                Log.v("msg", "keji");
                url = "http://v.juhe.cn/toutiao/index?type=keji&key=e68c6cd9bda71e5c21361e67da4a0cf9";
                setListviewAdapter();
                endAnimation();
                Log.v("msg", url);
                break;
        }
    }
}