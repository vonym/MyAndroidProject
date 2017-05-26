package com.example.videoapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by vonym on 17-3-10.
 */

public class SecondACtivity extends AppCompatActivity {
    private SurfaceView surfaceView;
    private MediaPlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        surfaceView = (SurfaceView) findViewById(R.id.surface);
        player = new MediaPlayer();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        try {
            player.setDataSource(path);
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(View view) {
        Button btn = (Button) view;
        String tag = btn.getText().toString();
        if (tag == "播放") {
            btn.setText("暂停");
            player.setDisplay(surfaceView.getHolder());
            player.start();
        } else if (tag == "暂停") {
            btn.setText("播放");
            player.pause();
        }
    }
}
