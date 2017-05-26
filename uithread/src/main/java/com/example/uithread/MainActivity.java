package com.example.uithread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private int index = 60;
    private static final int START_FLAG = 0x001;
    private static final int CURRENT_FLAG = 0x002;
    private static final int END_FLAG = 0x003;

    //主线程的handler，默认就拥有MessageQueue和Looper
    private Handler handler = new Handler() {
        /**
         * 消息处理
         * @param msg:子线程传递的数据
         */
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case START_FLAG:
                    button.setEnabled(false);
                    break;
                case CURRENT_FLAG:
                    button.setText(msg.arg1 + "秒后重发");
                    break;
                case END_FLAG:
                    button.setEnabled(true);
                    button.setText("重新发送");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn);
    }

    public void send(View view) {
        //按钮不能点击
        button.setEnabled(false);
        //倒计时
        new Thread() {
            @Override
            public void run() {
                handler.sendEmptyMessage(START_FLAG);
                while (index > 0) {
                    index--;
                    //Message message = new Message();
                    Message message = Message.obtain();//对messageQueue中的消息进行复用
                    message.what = CURRENT_FLAG;  //message的id
                    message.arg1 = index;
                    //message.arg2;
                    //message.obj;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(END_FLAG);
            }
        }.start();

        //恢复按钮
        index = 60;
    }
}
