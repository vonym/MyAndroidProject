package com.example.synactask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * params,progress,result
 * Created by vonym on 17-3-13.
 */

public class MyAsynacTask extends AsyncTask<Integer, Integer, String> {
    private ProgressBar progressBar;
    private TextView textView;

    public MyAsynacTask(ProgressBar progressBar, TextView textView) {
        this.progressBar = progressBar;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 主要负责后台耗时操作
     *
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(Integer... params) {
        for (int i = 0; i < 100; i++) {
            publishProgress(i);
            try {
                Thread.sleep(params[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "下载完成";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
        textView.setText("当前进度:"+values[0]+"%");
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        textView.setText(result);
    }
}
