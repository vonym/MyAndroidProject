package com.example.videoapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.video);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/";
        File file = new File(path, "movie.mp4");

        videoView.setVideoPath(file.getPath());
        videoView.setMediaController(new MediaController(this));
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.v("msg","what:"+what+"  extra:"+extra);
                return false;
            }
        });
    }

    public void start(View view) {
        videoView.start();
    }
}