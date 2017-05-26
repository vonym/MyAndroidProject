package com.example.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.adapter.MainVpAdapter;
import com.example.bookmanagersystem.R;
import com.example.fragment.AddFragment;
import com.example.fragment.ChangeFragment;
import com.example.fragment.DeleteFragment;
import com.example.fragment.QueryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private ViewPager mVp_show;
    private RadioGroup mRg_showRb;
    private RadioButton mRb_addBook, mRb_deleteBook, mRb_changeBook, mRb_querytBook;
    private Fragment mAddFragment, mDeleteFragment, mChangeFragment, mQueryFragment;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
        initListener();
    }

    private void initListener() {
        mVp_show.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //当界面被滑动时的监听
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //当界面被选中时的监听
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRb_addBook.setChecked(true);
                        break;
                    case 1:
                        mRb_deleteBook.setChecked(true);
                        break;
                    case 2:
                        mRb_changeBook.setChecked(true);
                        break;
                    case 3:
                        mRb_querytBook.setChecked(true);
                        break;
                }
            }

            //当界面滑动状态改变时的监听
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRg_showRb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //当RadioGroup中按钮改变时 的监听
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_addBook:
                        mVp_show.setCurrentItem(0);//设置当前的item
                        refresh(checkedId);
                        break;
                    case R.id.main_rb_deleteBook:
                        mVp_show.setCurrentItem(1);
                        refresh(checkedId);
                        break;
                    case R.id.main_rb_changeeBook:
                        mVp_show.setCurrentItem(2);
                        refresh(checkedId);
                        break;
                    case R.id.main_rb_querytBook:
                        mVp_show.setCurrentItem(3);
                        refresh(checkedId);
                        break;
                }
            }
        });
    }

    private void refresh(int id) {
        switch (id) {
            case R.id.main_rb_addBook:
                backgroundAllBlack();
                mRb_addBook.setTextColor(Color.RED);//另外一种方法color.pareColor("#ff0000")
                break;
            case R.id.main_rb_deleteBook:
                backgroundAllBlack();
                mRb_deleteBook.setTextColor(Color.RED);
                break;
            case R.id.main_rb_changeeBook:
                backgroundAllBlack();
                mRb_changeBook.setTextColor(Color.RED);
                break;
            case R.id.main_rb_querytBook:
                backgroundAllBlack();
                mRb_querytBook.setTextColor(Color.RED);
                break;
        }
    }

    private void backgroundAllBlack() {
        mRb_addBook.setTextColor(Color.BLACK);
        mRb_deleteBook.setTextColor(Color.BLACK);
        mRb_changeBook.setTextColor(Color.BLACK);
        mRb_querytBook.setTextColor(Color.BLACK);
    }

    private void initAdapter() {
        mVp_show.setAdapter(new MainVpAdapter(getSupportFragmentManager(), mFragments));
    }

    private void initData() {
        mAddFragment = new AddFragment();
        mDeleteFragment = new DeleteFragment();
        mChangeFragment = new ChangeFragment();
        mQueryFragment = new QueryFragment();
        mFragments = new ArrayList<>();
        mFragments.add(mAddFragment);
        mFragments.add(mDeleteFragment);
        mFragments.add(mChangeFragment);
        mFragments.add(mQueryFragment);
    }

    private void initView() {
        mVp_show = (ViewPager) findViewById(R.id.main_vp);
        mRg_showRb = (RadioGroup) findViewById(R.id.main_rg);
        mRb_addBook = (RadioButton) findViewById(R.id.main_rb_addBook);
        mRb_deleteBook = (RadioButton) findViewById(R.id.main_rb_deleteBook);
        mRb_changeBook = (RadioButton) findViewById(R.id.main_rb_changeeBook);
        mRb_querytBook = (RadioButton) findViewById(R.id.main_rb_querytBook);
    }
}
