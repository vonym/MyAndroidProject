package com.example.cubic;

import android.view.animation.Interpolator;

/**
 * Created by vonym on 2017/1/25.
 */

public class BezierInterpolator implements Interpolator {
    private float p1, p2;

    public BezierInterpolator(float p1, float p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public float getInterpolation(float t) {
        //曲线的生成
        return 0 * (1 - t) * (1 - t) * (1 - t) + 3 * p1 * t * (1 - t) * (1 - t) + 3 * p2 * t * t * (1 - t) + t * t * t;
    }
}
