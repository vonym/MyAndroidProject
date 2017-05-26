package com.example.photos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yumao on 17-2-27.
 */

public class ItemActivity extends AppCompatActivity {
    private Intent intent;
    private SqliteHelper helper;
    private SQLiteDatabase db;
    private ImageView img;
    private Gallery gallery;
    private ImageDao imageDao;
    private List<ImageBean> list_imgs;
    private int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        init();
        getFlag();
        gallery.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list_imgs.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView = new ImageView(ItemActivity.this);
                imageView.setAdjustViewBounds(true);
                imageView.setImageResource(list_imgs.get(position).getImg());
                return imageView;
            }
        });
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                img.setImageResource(list_imgs.get(position).getImg());
                setTitle(list_imgs.get(position).getName());
                mPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                img.setImageResource(R.mipmap.ic_launcher);
            }
        });
    }

    private void init() {
        intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        helper = new SqliteHelper(ItemActivity.this);
        list_imgs = new ArrayList<>();
        img = (ImageView) findViewById(R.id.img);
        gallery = (Gallery) findViewById(R.id.gallery);
        imageDao = new ImageDao(ItemActivity.this);
    }

    private void getFlag() {
        db = helper.getReadableDatabase();
        switch (intent.getIntExtra("flag", 1)) {
            case 1:
                list_imgs = imageDao.queryImg("人物");
                break;
            case 2:
                list_imgs = imageDao.queryImg("风景");
                break;
            case 3:
                list_imgs = imageDao.queryImg("动漫");
                break;
            case 4:
                list_imgs = imageDao.queryImg("动物");
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "更改类型");
        menu.add(0, 2, 0, "删除图片");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case 1:
                View view = View.inflate(ItemActivity.this, R.layout.dialog_item, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ItemActivity.this);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                final EditText type_Edit = (EditText) view.findViewById(R.id.type_Edit);
                alertDialog.show();
                Button btn_submit = (Button) view.findViewById(R.id.btn_submit);
                Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String type = type_Edit.getText().toString();
                        if ("人物".equals(type) || "风景".equals(type) || "动漫".equals(type) || "动物".equals(type)) {
                            //修改图片的类型
                            imageDao.updateImg(list_imgs.get(mPosition).getImg(), type);
                            Toast.makeText(ItemActivity.this, "更改成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ItemActivity.this, "类型错误", Toast.LENGTH_SHORT).show();
                        }
                        type_Edit.setText("");
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                break;
            case 2:
                new AlertDialog.Builder(ItemActivity.this).setIcon(R.mipmap.ic_launcher)
                        .setTitle("Photos").setMessage("确定删除")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ItemActivity.this, list_imgs.get(mPosition).getImg() + "", Toast.LENGTH_SHORT).show();
                                imageDao.deleteImg(list_imgs.get(mPosition).getImg());
                                finish();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
