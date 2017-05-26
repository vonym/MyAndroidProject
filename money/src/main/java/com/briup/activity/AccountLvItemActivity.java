package com.briup.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.briup.bean.AccountRecord;
import com.briup.db.CardDao;
import com.briup.db.RecordDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vonym on 17-1-11.
 */
public class AccountLvItemActivity extends Activity implements View.OnClickListener {
    private ImageView mIv_back, mIv_content, mIv_name, mIv_edit, mIv_up, mIv_calender;
    private EditText mEt_money;
    private TextView mTv_content, mTv_name, mTv_calender, mTv_calender2;
    private Button mBtn_save;
    private Intent intent;
    private int position, img, Year, Month, DayOfMonth;
    ;
    private float money;
    private Calendar calendar=Calendar.getInstance();
    private RecordDao recordDao;
    private CardDao cardDao;
    private List<AccountRecord> records;
    private String content, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_lv_item_layout);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        calendar = Calendar.getInstance();
        mIv_back.setOnClickListener(this);
        mIv_edit.setOnClickListener(this);
        mIv_up.setOnClickListener(this);
        mTv_calender2.setOnClickListener(this);
        mBtn_save.setOnClickListener(this);
    }

    private void initData() {
        mIv_content.setImageResource(img);
        mTv_content.setText(content);
/**
 * 账户默认4个支付方式
 */
        mTv_name.setText(name);
        mEt_money.setText(money + "");
        mTv_calender.setText(Month + "月" + DayOfMonth + "日");
        mTv_calender2.setText(calendar.get(Calendar.DAY_OF_MONTH)+"");
    }

    private void initView() {
        mIv_back = (ImageView) findViewById(R.id.account_lv_item_layout_iv_back);
        mIv_content = (ImageView) findViewById(R.id.account_lv_item_layout_iv_conten);
        mTv_content = (TextView) findViewById(R.id.account_lv_item_layout_tv_content);
        mIv_name = (ImageView) findViewById(R.id.account_lv_item_layout_iv_name);
        mTv_name = (TextView) findViewById(R.id.account_lv_item_layout_tv_name);
        mEt_money = (EditText) findViewById(R.id.account_lv_item_layout_et_money);
        mIv_edit = (ImageView) findViewById(R.id.account_lv_item_layout_iv_edit);
        mIv_up = (ImageView) findViewById(R.id.account_lv_item_layout_iv_up);
        mTv_calender = (TextView) findViewById(R.id.account_lv_item_layout_tv_calender);
        mTv_calender2 = (TextView) findViewById(R.id.account_lv_item_layout_tv_calender2);
        mBtn_save = (Button) findViewById(R.id.account_lv_item_layout_btn);
        intent = getIntent();
        position = intent.getIntExtra("position", 0);
        recordDao = new RecordDao(AccountLvItemActivity.this);
        cardDao = new CardDao(AccountLvItemActivity.this);
        records = new ArrayList<>();
        try {
            records = recordDao.queryAllRecord();
            content = records.get(position).getContent();
            name = cardDao.findNameById(records.get(position).getUser_id());
            money = records.get(position).getMoney();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(records.get(position).getTime());
            SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
            SimpleDateFormat MM = new SimpleDateFormat("MM");
            SimpleDateFormat dd = new SimpleDateFormat("dd");
            Year = Integer.parseInt(yyyy.format(date));
            Month = Integer.parseInt(MM.format(date));
            DayOfMonth = Integer.parseInt(dd.format(date));
            img = records.get(position).getImg();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_lv_item_layout_iv_back:
                finish();
                break;
            case R.id.account_lv_item_layout_iv_edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountLvItemActivity.this);
                View view = View.inflate(AccountLvItemActivity.this, R.layout.dialog_zhanghuleixing, null);
                builder.setIcon(R.drawable.ic_launcher).setTitle("选择账户类型").
                        setView(view).
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                final AlertDialog dialog = builder.create();
                dialog.show();
                LinearLayout ll_cash = (LinearLayout) view.findViewById(R.id.zhanghuleixing_ll_cash);
                LinearLayout ll_chuxuka = (LinearLayout) view.findViewById(R.id.zhanghuleixing_ll_chuxuka);
                LinearLayout ll_creditcard = (LinearLayout) view.findViewById(R.id.zhanghuleixing_ll_creditcard);
                LinearLayout ll_wangluozhifu = (LinearLayout) view.findViewById(R.id.zhanghuleixing_ll_wangluozhifu);
                ll_cash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mIv_name.setImageResource(R.drawable.ft_cash);
                        name = "现金";
                        mTv_name.setText(name);
                    }
                });
                ll_chuxuka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mIv_name.setImageResource(R.drawable.ft_chuxuka);
                        name = "储蓄卡";
                        mTv_name.setText(name);
                    }
                });
                ll_creditcard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mIv_name.setImageResource(R.drawable.ft_creditcard);
                        name = "信用卡";
                        mTv_name.setText(name);
                    }
                });
                ll_wangluozhifu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        mIv_name.setImageResource(R.drawable.ft_wangluochongzhi);
                        name = "支付宝";
                        mTv_name.setText(name);
                    }
                });
                break;
            case R.id.account_lv_item_layout_iv_up:
                mEt_money.setEnabled(true);
                break;
            case R.id.account_lv_item_layout_tv_calender2:
                new DatePickerDialog(AccountLvItemActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mTv_calender.setText((month + 1) + "月" + dayOfMonth + "日");
                        Year = year;
                        Month = month;
                        DayOfMonth = dayOfMonth;
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.account_lv_item_layout_btn:
                String time = Year + Month + DayOfMonth + "";
                recordDao.updateRecord(content, name, money, time);
                finish();
                break;
        }
    }
}
