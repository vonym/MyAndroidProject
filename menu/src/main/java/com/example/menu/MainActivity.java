package com.example.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        //给控件注册上下文菜单

        tv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item1:
                                Toast.makeText(MainActivity.this, "扫一扫", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.item2:
                                Toast.makeText(MainActivity.this, "添加朋友", Toast.LENGTH_SHORT).show();
                                break;

                        }
                        return false;
                    }
                });
            }
        });

        registerForContextMenu(tv);
    }

    /**
     * 用来去制作选项菜单 三个小点：overflow 两种方式： 1.java代码 2.菜单的布局文件
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        // 添加一个普通菜单项
//		menu.add(0, 1, 1, "扫一扫");
//		menu.add(0, 2, 2, "添加朋友");
//		//添加含有子菜单的菜单项
//		SubMenu subMenu = menu.addSubMenu(0, 3, 3, "字体颜色");
//		subMenu.add(0, 4, 3, "红色");
//		subMenu.add(0, 5, 2, "绿色");
//		subMenu.add(0, 6, 1, "蓝色");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(this, "添加朋友", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
}
