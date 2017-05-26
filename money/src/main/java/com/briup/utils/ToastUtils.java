package com.briup.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vonym on 17-1-5.
 */

public class ToastUtils {
    public static void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
