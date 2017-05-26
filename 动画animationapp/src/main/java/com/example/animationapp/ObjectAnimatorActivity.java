package com.example.animationapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonym on 17-3-10.
 */

public class ObjectAnimatorActivity extends AppCompatActivity {
    private ImageView iv_a, iv_b, iv_c, iv_d;
    private List<ImageView> imgs;
    private boolean is_open = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.objectanim_main);
        initView();

    }

    //    public void start(View view) {
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f);
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "translationY", 0f, 200f);
//        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.5f);
//        AnimatorSet animatorSet = new AnimatorSet();
//        //一起玩
//        animatorSet.playTogether(animator1, animator2, animator3);
//        animatorSet.play(animator1).with(animator2);
//
//        //animator1.start();
//        animatorSet.start();
//        //有选择的进行时间监听
//        animatorSet.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                Toast.makeText(ObjectAnimatorActivity.this, "结束", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void start(View view) {
        switch (view.getId()) {
            case R.id.iv_a:
                if (is_open) {
                    endAnimation();
                    Toast.makeText(ObjectAnimatorActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                } else {
                    startAnimation();
                    Toast.makeText(ObjectAnimatorActivity.this, "开始", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_b:

                break;
            case R.id.iv_c:
                break;
            case R.id.iv_d:
                break;
        }
    }

    private void startAnimation() {
        for (int i = 0; i < imgs.size(); i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imgs.get(i), "translationY", 0f, 150f * i);
            animator.setStartDelay(200 * i);
            animator.setDuration(150);
            animator.start();
        }
        is_open = true;
    }

    private void endAnimation() {
        for (int i = 0; i < imgs.size(); i++) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(imgs.get(i), "translationY", -150f * i, 0f);
            animator.setStartDelay(200 * i);
            animator.setDuration(150);
            animator.start();
        }
        is_open = false;
    }

    private void initView() {
        iv_a = (ImageView) findViewById(R.id.iv_a);
        iv_b = (ImageView) findViewById(R.id.iv_b);
        iv_c = (ImageView) findViewById(R.id.iv_c);
        iv_d = (ImageView) findViewById(R.id.iv_d);
        imgs = new ArrayList<>();
        imgs.add(iv_a);
        imgs.add(iv_b);
        imgs.add(iv_c);
        imgs.add(iv_d);
    }
}
