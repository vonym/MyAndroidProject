package com.example.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.iv);
    }
    public void draw(View view){
        Bitmap bitmap=Bitmap.createBitmap(500,500, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20f);//画笔大小

        //直线
        canvas.drawLine(200,200,500,0,paint);
        //矩形
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(100,100,300,300,paint);
        //圆
        canvas.drawCircle(500,500,100,paint);
        //扇形
        RectF rectF=new RectF(100,100,300,400);
        canvas.drawArc(rectF,0,90,true,paint);

        //不规则图形
        Path path=new Path();
        path.moveTo(200,200);
        path.lineTo(100,100);
        path.lineTo(500,0);
        path.lineTo(200,200);
        path.close();
        canvas.drawPath(path,paint);

        imageView.setImageBitmap(bitmap);
    }
}
