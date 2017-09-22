package com.example.cubic;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ListView;

/**
 * Created by vonym on 2017/8/14 0014.
 */

public class CustomListView extends ListView {
    public CustomListView(Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
