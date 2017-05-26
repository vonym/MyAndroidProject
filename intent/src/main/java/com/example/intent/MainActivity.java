package com.example.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	public void btn(View view){
		/*
		Intent intent=new Intent();
		ComponentName componentName=new ComponentName(this, SecondActivity.class);
		intent.setComponent(componentName);
		startActivity(intent);
		*/
		/*
		Intent intent=new Intent();
		intent.setClass(this, SecondActivity.class);
		startActivity(intent);
		*/
		//最简单，最常用(显示意图：一般用于程序内部的跳转)
		/*
		Intent intent=new Intent(this,SecondActivity.class);
		startActivity(intent);
		*/
		Intent intent=new Intent();
		//intent只能有一个action，如果写多个，后面的会覆盖前面的
		intent.setAction("da");
		intent.addCategory("yongli");
		intent.addCategory("memeda");
		//data和type不能分开设，否则后面的会覆盖前面的
		intent.setDataAndType(Uri.parse("briup://192.168.1.1:8888/thirdActivity"), "abc/def");
	    Bundle bundle=new Bundle();
	    bundle.putString("name", "小明");
		intent.putExtra("bundle", bundle);
		startActivity(intent);
		
	}
	
	public void btn1(View view){
		Intent intent=new Intent();
		/*
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:18635207734"));
		startActivity(intent);
		*/
		intent.setAction(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("sms:10086"));
		  intent.putExtra("sms_body", "请为我充100的话费，谢谢"); 
		startActivity(intent);
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
