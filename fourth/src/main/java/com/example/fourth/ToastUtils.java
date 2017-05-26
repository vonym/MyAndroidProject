package com.example.fourth;

import android.content.Context;
import android.widget.Toast;

/**
 * 显示Toast
 * Created by vonym on 16-12-29.
 */

public class ToastUtils {
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
