package com.example.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView1, imageView2;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = (ImageView) findViewById(R.id.iv1);
        imageView2 = (ImageView) findViewById(R.id.iv2);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/img.jpg";
        bitmap = BitmapFactory.decodeFile(path);
        imageView1.setImageBitmap(bitmap);

    }

    public void test(View view) {

        //1.准备画纸
        Bitmap copyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //2.将画纸固定在画板上
        Canvas canvas = new Canvas(copyBitmap);
        //3.画笔
        Paint paint = new Paint();
        Matrix matrix = new Matrix();


        //缩放
        //matrix.setScale(2f, 2f);
        //位移
        //matrix.setTranslate(100f,100f);
        //旋转
        //matrix.setRotate(45);
        //matrix.setRotate(45,bitmap.getWidth()/2,bitmap.getHeight()/2);
        //镜面
        matrix.setScale(-1f, 1f);
        matrix.postTranslate(bitmap.getWidth(), 0);
        canvas.drawBitmap(bitmap, matrix, paint);


        imageView2.setImageBitmap(copyBitmap);

    }
}
