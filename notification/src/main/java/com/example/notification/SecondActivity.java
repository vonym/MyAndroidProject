package com.example.notification;

import android.app.Activity;

/**
 * Created by vonym on 16-12-22.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.second_layout);
    }
}
