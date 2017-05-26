package com.example.iostream;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {
    private EditText mEt_write, mEt_read;
    private final String FILE_NAME = "feng.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEt_write = (EditText) findViewById(R.id.main_et_writedata);
        mEt_read = (EditText) findViewById(R.id.main_et_readdata);
    }

    public void write(View view) {
        String content = mEt_write.getText().toString();
        try {
            FileOutputStream out = new FileOutputStream(getFilesDir() + "/" + FILE_NAME);
            /**getFilesDir()获取应用程序的数据文件的路径
             * "/":表示在某个路径名下级目录
             */
            PrintStream ps = new PrintStream(out);
            ps.write(content.getBytes());
            ps.close();
            out.close();
            ToastUtils.showToast(this, "写入成功");
            mEt_write.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(View view) {
        try {
            FileInputStream fis = new FileInputStream(getFilesDir() + "/" + FILE_NAME);
            byte[] by = new byte[1024];
            int len = -1;
            StringBuilder sb = new StringBuilder();//定义可变长的字符串
            //循环读取
            while ((len = fis.read(by)) != -1) {
                sb.append(new String(by, 0, len));
            }
            ToastUtils.showToast(this, "读取成功");
            //拿到读取的字符串
            String content = sb.toString();
            //显示在界面上
            mEt_read.setText(content);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast(this, "写入失败");
        }
    }
}
