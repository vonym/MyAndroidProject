package com.example.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * 自己创建Activity 1.写个类继承Activity 
 * 2.重写onCreate方法
 * 3.在onCreate方法中调用setContentView(),将布局文件加载到当前Activity中
 * 4.新建一个属于当前Activity的布局文件
 * 5.到配置文件中配置当前Activity
 */
public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}

}
