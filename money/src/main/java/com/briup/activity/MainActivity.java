package com.briup.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.briup.adapter.MainVpAdapter;
import com.briup.db.UserDao;
import com.briup.fragment.accountFragment;
import com.briup.fragment.formFragment;
import com.briup.fragment.foundsFragment;
import com.briup.fragment.mineFragment;
import com.briup.utils.SharePreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager mVp;
    private List<Fragment> fragments;
    private LinearLayout mLl_account, mLl_form, mLl_founds, mLl_mine;
    private ImageView mIv_account, mIv_form, mIv_founds, mIv_mine;
    private TextView mTv_account, mTv_form, mTv_founds, mTv_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        mVp.setAdapter(new MainVpAdapter(getSupportFragmentManager(), fragments));
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initData() {
        fragments.add(new accountFragment());
        fragments.add(new formFragment());
        fragments.add(new foundsFragment());
        fragments.add(new mineFragment());

    }

    private void initListener() {
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        refresh();
                        mIv_account.setImageResource(R.drawable.tab_accounte);
                        mTv_account.setTextColor(getResources().getColor(R.color.colorTheme));
                        break;
                    case 1:
                        refresh();
                        mIv_form.setImageResource(R.drawable.tab_form);
                        mTv_form.setTextColor(getResources().getColor(R.color.colorTheme));
                        break;
                    case 2:
                        refresh();
                        mIv_founds.setImageResource(R.drawable.tab_founds);
                        mTv_founds.setTextColor(getResources().getColor(R.color.colorTheme));
                        break;
                    case 3:
                        refresh();
                        mIv_mine.setImageResource(R.drawable.tab_mine);
                        mTv_mine.setTextColor(getResources().getColor(R.color.colorTheme));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mLl_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVp.setCurrentItem(0);
                refresh();
                mIv_account.setImageResource(R.drawable.tab_accounte);
                mTv_account.setTextColor(getResources().getColor(R.color.colorTheme));
            }
        });
        mLl_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVp.setCurrentItem(1);
                refresh();
                mIv_form.setImageResource(R.drawable.tab_form);
                mTv_form.setTextColor(getResources().getColor(R.color.colorTheme));
            }
        });
        mLl_founds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVp.setCurrentItem(2);
                refresh();
                mIv_founds.setImageResource(R.drawable.tab_founds);
                mTv_founds.setTextColor(getResources().getColor(R.color.colorTheme));
            }
        });
        mLl_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVp.setCurrentItem(3);
                refresh();
                mIv_mine.setImageResource(R.drawable.tab_mine);
                mTv_mine.setTextColor(getResources().getColor(R.color.colorTheme));
            }
        });
    }

    private void refresh() {
        mIv_account.setImageResource(R.drawable.tab_accounte2);
        mIv_form.setImageResource(R.drawable.tab_form2);
        mIv_founds.setImageResource(R.drawable.tab_founds2);
        mIv_mine.setImageResource(R.drawable.tab_mine2);
        mTv_account.setTextColor(getResources().getColor(R.color.colorGray));
        mTv_form.setTextColor(getResources().getColor(R.color.colorGray));
        mTv_founds.setTextColor(getResources().getColor(R.color.colorGray));
        mTv_mine.setTextColor(getResources().getColor(R.color.colorGray));
    }

    public void initView() {
        fragments = new ArrayList<>();
        mVp = (ViewPager) findViewById(R.id.main_vp);
        mLl_account = (LinearLayout) findViewById(R.id.main_ll_account);
        mLl_form = (LinearLayout) findViewById(R.id.main_ll_form);
        mLl_founds = (LinearLayout) findViewById(R.id.main_ll_founds);
        mLl_mine = (LinearLayout) findViewById(R.id.main_ll_mine);
        mIv_account = (ImageView) findViewById(R.id.main_iv_account);
        mIv_form = (ImageView) findViewById(R.id.main_iv_form);
        mIv_founds = (ImageView) findViewById(R.id.main_iv_founds);
        mIv_mine = (ImageView) findViewById(R.id.main_iv_mine);
        mTv_account = (TextView) findViewById(R.id.main_tv_account);
        mTv_form = (TextView) findViewById(R.id.main_tv_form);
        mTv_founds = (TextView) findViewById(R.id.main_tv_founds);
        mTv_mine = (TextView) findViewById(R.id.main_tv_mine);
    }
}
