package com.example.iqiyi;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import ui.DaohangFragment;
import ui.FaxianFragment;
import ui.LixianguankanFragment;
import ui.TuijianFragment;
import ui.WodeFragment;

public class MainActivity extends Activity implements OnClickListener {

    private TuijianFragment tuijianFragment;
    private DaohangFragment daohangFragment;
    private FaxianFragment faxianFragment;
    private WodeFragment wodeFragment;
    private LixianguankanFragment lixianguankanFragment;
    private ImageView iv_tuijian;
    private ImageView iv_daohang;
    private ImageView iv_faxian;
    private ImageView iv_wode;
    private ImageView iv_lixian;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化布局元素
        initViews();
        fm = getFragmentManager();
        setTabSelection(0);
    }

    private void initViews() {
        iv_tuijian = (ImageView) findViewById(R.id.iv_tuijian);
        iv_daohang = (ImageView) findViewById(R.id.iv_daohang);
        iv_faxian = (ImageView) findViewById(R.id.iv_faxian);
        iv_wode = (ImageView) findViewById(R.id.iv_wode);
        iv_lixian = (ImageView) findViewById(R.id.iv_lixian);
        iv_tuijian.setOnClickListener(this);
        iv_daohang.setOnClickListener(this);
        iv_faxian.setOnClickListener(this);
        iv_wode.setOnClickListener(this);
        iv_lixian.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_tuijian:
                setTabSelection(0);
                break;
            case R.id.iv_daohang:
                setTabSelection(1);
                break;
            case R.id.iv_faxian:
                setTabSelection(2);
                break;
            case R.id.iv_wode:
                setTabSelection(3);
                break;
            case R.id.iv_lixian:
                setTabSelection(4);
                break;
        }
    }

    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                iv_tuijian.setImageResource(R.drawable.tuijian2);
                if (tuijianFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    tuijianFragment = new TuijianFragment();
                    transaction.add(R.id.content, tuijianFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(tuijianFragment);
                }
                break;
            case 1:
                iv_daohang.setImageResource(R.drawable.daohang2);

                if (daohangFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    daohangFragment = new DaohangFragment();
                    transaction.add(R.id.content, daohangFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(daohangFragment);
                }
                break;
            case 2:
                iv_faxian.setImageResource(R.drawable.faxian2);
                if (faxianFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    faxianFragment = new FaxianFragment();
                    transaction.add(R.id.content, faxianFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(faxianFragment);
                }
                break;
            case 3:
                iv_wode.setImageResource(R.drawable.wode2);
                if (wodeFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    wodeFragment = new WodeFragment();
                    transaction.add(R.id.content, wodeFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(wodeFragment);
                }
                break;
            case 4:
                iv_lixian.setImageResource(R.drawable.lixian2);
                if (lixianguankanFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    lixianguankanFragment = new LixianguankanFragment();
                    transaction.add(R.id.content, lixianguankanFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(lixianguankanFragment);
                }
                break;

        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        iv_tuijian.setImageResource(R.drawable.tuijian1);
        iv_daohang.setImageResource(R.drawable.daohang1);
        iv_faxian.setImageResource(R.drawable.faxian1);
        iv_wode.setImageResource(R.drawable.wode1);
        iv_lixian.setImageResource(R.drawable.lixian1);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (tuijianFragment != null) {
            transaction.hide(tuijianFragment);
        }
        if (daohangFragment != null) {
            transaction.hide(daohangFragment);
        }
        if (faxianFragment != null) {
            transaction.hide(faxianFragment);
        }
        if (wodeFragment != null) {
            transaction.hide(wodeFragment);
        }
        if (lixianguankanFragment != null) {
            transaction.hide(lixianguankanFragment);
        }

    }

}