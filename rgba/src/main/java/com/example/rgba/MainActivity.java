package com.example.rgba;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar red_Sb, green_Sb, blue_Sb;
    private ImageView imageView;
    private Bitmap bitmap;
    private float red, green, blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.iv);

        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        red_Sb = (SeekBar) findViewById(R.id.red_sb);
        green_Sb = (SeekBar) findViewById(R.id.green_sb);
        blue_Sb = (SeekBar) findViewById(R.id.blue_sb);
        red_Sb.setOnSeekBarChangeListener(this);
        green_Sb.setOnSeekBarChangeListener(this);
        blue_Sb.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.red_sb:
                red = progress / 50f;
                break;
            case R.id.green_sb:
                green = progress / 50f;
                break;
            case R.id.blue_sb:
                blue = progress / 50f;
                break;
        }
        float[] colors = {
                red, 0, 0, 0, 0,
                0, green, 0, 0, 0,
                0, 0, blue, 0, 0,
                0, 0, 0, 255, 0};
        Bitmap copyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(copyBitmap);
        Paint paint = new Paint();
        Matrix matrix = new Matrix();
        paint.setColorFilter(new ColorMatrixColorFilter(colors));
        canvas.drawBitmap(copyBitmap, matrix, paint);
        imageView.setImageBitmap(copyBitmap);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
