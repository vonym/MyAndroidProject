package com.example.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vonym on 17-1-3.
 */

public class ToastUtils {
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
