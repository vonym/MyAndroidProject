package com.example.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vonym on 17-3-16.
 */

public class ThirdActivity extends AppCompatActivity {
    private ImageView imageView;
    private Bitmap bmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        imageView = (ImageView) findViewById(R.id.iv);
        new Thread() {
            @Override
            public void run() {
                String url = "http://07.imgmini.eastday.com/mobile/20170316/20170316193407_aabc949fa3c2fb1bfac995c20d9e42fd_1_mwpm_03200403.jpeg";
                URL u = null;
                try {
                    u = new URL(url);
                    bmp = BitmapFactory.decodeStream(u.openStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bmp);
                    }
                });
            }
        }.start();

    }

}