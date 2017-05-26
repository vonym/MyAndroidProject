package com.example.animationapp;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FrameAnimationActivity extends AppCompatActivity {
    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    private boolean is_start = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frameanimation_main);
        imageView = (ImageView) findViewById(R.id.iv);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
    }

    public void start(View view) {
        Button btn = (Button) view;
        if (is_start) {
            btn.setText("暂停");
            animationDrawable.start();
            is_start = false;
        } else {
            btn.setText("播放");
            animationDrawable.stop();
            is_start = true;
        }
    }
}
