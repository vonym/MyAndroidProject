package com.example.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 两种方式加载fragment：
 * 1.静态加载fragment
 * 2.动态加载fragment
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img1, img2, img3;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FirstFramgent firstFramgent;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        firstFramgent = new FirstFramgent();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.commit();
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        cleanFragment();
        switch (v.getId()) {
            case R.id.img1:
                Log.v("msg", "img1");
                transaction.add(R.id.frame, new FirstFramgent());
                break;
            case R.id.img2:
                transaction.add(R.id.frame, new SecondFragment());
                break;
            case R.id.img3:
                transaction.add(R.id.frame, new ThirdFragment());
                break;

        }
    }

    public void cleanFragment() {
        if (firstFramgent != null) {
            transaction.remove(firstFramgent);
        }
        if (secondFragment != null) {
            transaction.remove(secondFragment);
        }
        if (thirdFragment != null) {
            transaction.remove(thirdFragment);
        }
    }
}
