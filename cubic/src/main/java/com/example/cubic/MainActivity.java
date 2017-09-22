package com.example.cubic;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageView;
    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        width = getResources().getDisplayMetrics().widthPixels;
        height = getResources().getDisplayMetrics().heightPixels-200;

        mImageView = (ImageView) findViewById(R.id.mIv);
        mImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mIv) {
            ValueAnimator animator = getValueAnimator();
            animator.start();
        }
    }

    private ValueAnimator getValueAnimator() {
        PointF pointF0 = new PointF(0, 100);
        PointF pointF1 = new PointF(100, 120);
        PointF pointF2 = new PointF(500,200);
        PointF pointF3 = new PointF(700, 100);

        //通过贝塞尔曲线公式，自定义估值器
        BezierEvaluator evaluator = new BezierEvaluator(pointF1, pointF2);
        //将估值器传入属性动画，不断的修改控件的坐标
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, pointF0, pointF3);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                mImageView.setX(pointF.x);
                mImageView.setY(pointF.y);
            }
        });
        animator.setTarget(mImageView);
        animator.setDuration(3000);
        //同样，为了美观我们还可以添加加速度，减速度，弹射等效果（插值器）
        animator.setInterpolator(new BezierInterpolator(1.61f, -0.26f));
        return animator;
    }
}
