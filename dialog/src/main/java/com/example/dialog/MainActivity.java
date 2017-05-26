package com.example.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1(View view) {
        showAlertDialog();
    }

    /*第一步：创建AlertDialog.Builder
    * 第二步：使用builder设置title,icon,message,按钮
    * 第三步：调用buildre的creat().show()方法*/
    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("确定退出？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    public void btn2(View view) {
        showDateDialog();
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Toast.makeText(MainActivity.this, "你选择的日期：" + i + "/" + i1 + "/" + i2, Toast.LENGTH_SHORT).show();
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void btn3(View view) {
        showMyDialog();
    }

    private void showMyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(MainActivity.this, R.layout.mydialog, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        final EditText psw_Edit = (EditText) view.findViewById(R.id.psw_Edit);
        final EditText psw_true_Edit = (EditText) view.findViewById(R.id.psw_true_Edit);
        Button btn_submit = (Button) view.findViewById(R.id.btn_submit);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (("").equals(psw_Edit.getText().toString()) || ("").equals(psw_true_Edit.getText().toString())) {
                    Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (psw_Edit.getText().toString().equals(psw_true_Edit.getText().toString())) {
                        Toast.makeText(MainActivity.this, "密码为：" + psw_Edit.getText().toString(), Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    } else
                        Toast.makeText(MainActivity.this, "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}
