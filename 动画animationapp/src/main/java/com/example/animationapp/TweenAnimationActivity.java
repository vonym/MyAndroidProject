package com.example.animationapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by vonym on 17-3-10.
 */

public class TweenAnimationActivity extends AppCompatActivity {
    private Animation animation;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweenanimation_main);
        imageView = (ImageView) findViewById(R.id.iv);
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        //animation = AnimationUtils.loadAnimation(this, R.anim.set_anim);

    }

    public void start(View view) {
        animation.setFillAfter(true);//停留在动画介绍结束状态
        imageView.startAnimation(animation);
    }
}
