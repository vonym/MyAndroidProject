package com.example.app;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private SurfaceView surfaceView;
    private MediaRecorder mediaRecorder;
    private boolean is_start = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surface);

    }

    public void start(View view) {
        mediaRecorder = new MediaRecorder();
        switch (view.getId()) {
            case R.id.btn_start:
                if (!is_start) {
                    ((Button) findViewById(R.id.btn_start)).setText("停止录制");
                    is_start = true;
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setAudioSource(MediaRecorder.VideoSource.CAMERA);
                    mediaRecorder.setAudioEncoder(MediaRecorder.VideoEncoder.H264);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setVideoFrameRate(20);
                    mediaRecorder.setVideoSize(176, 144);
                    mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
                    mediaRecorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.mp4");
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ((Button) findViewById(R.id.btn_start)).setText("开始录制");
                    mediaRecorder.stop();
                    if (mediaRecorder != null) {
                        is_start = false;
                        mediaRecorder.release();
                    }
                }
                break;
            case R.id.btn_stop:
                break;
        }
    }
}
