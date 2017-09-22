package com.example.cubic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 贝塞尔曲线的应用
 * Created by vonym on 2017/1/25.
 */

public class CustomView extends View {
    private float mX;
    private float mY;
    private Paint mGesturePaint = new Paint();
    private Path mPath = new Path();

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(8);
        mGesturePaint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mX = event.getX();
                mY = event.getY();
                mPath.moveTo(mX, mY);
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                float preX = mX;
                float preY = mY;
                float dX = Math.abs(x - preX);
                float dY = Math.abs(y - preY);
                if (dX >= 3 || dY >= 3) {
                    float cX = (x + preX) / 2;
                    float cY = (y + preY) / 2;
                    mPath.quadTo(preX, preY, cX, cY);
                    mX = x;
                    mY = y;
                }
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mGesturePaint);
    }
}
