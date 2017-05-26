package com.briup.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.briup.adapter.AccountGvAdapter;
import com.briup.bean.AccountRecord;
import com.briup.db.RecordDao;
import com.briup.db.UserDao;
import com.briup.utils.SharePreferenceUtils;
import com.briup.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by vonym on 17-1-9.
 */
public class AccountActivity extends Activity implements View.OnClickListener {
    private ImageView mIv_back, mIv_item, mIv_mine;
    private RelativeLayout mRl;
    private LinearLayout mLl, mLl_ok;
    private EditText mEt_num;
    private TextView mTv_mine, mTv_calender;
    private Button mBtn_zhichu, mBtn_shouru;
    private GridView mGv;
    private int[] zhichu_imgs = {R.drawable.bt_food, R.drawable.bt_wine, R.drawable.bt_car,
            R.drawable.bt_shopping, R.drawable.bt_yule, R.drawable.bt_kuisun,
            R.drawable.bt_service, R.drawable.bt_chongzhi, R.drawable.bt_madecine,
            R.drawable.bt_house, R.drawable.bt_water, R.drawable.bt_shicai};
    private int[] shouru_imgs = {R.drawable.bt_wages, R.drawable.bt_bouns, R.drawable.bt_fuli,
            R.drawable.bt_invest, R.drawable.bt_hongbao, R.drawable.bt_jianzhi,
            R.drawable.bt_shenghuofei, R.drawable.bt_baoxiao, R.drawable.bt_tuikuan,
            R.drawable.bt_gongjijin, R.drawable.bt_shebao, R.drawable.bt_yiwai};
    private String[] zhichu_strs = {"餐饮", "烟酒", "交通", "购物", "娱乐", "投资亏损",
            "生活服务", "充值", "医药", "住房", "水电费", "食材"};
    private String[] shouru_strs = {"工资", "奖金", "福利", "投资收益", "红包", "兼职",
            "生活费", "报销", "退款", "公积金", "社保金", "意外收获"};
    private int[] backgrounds = {R.color.colorOrange, R.color.colorAccent, R.color.colorGray, R.color.colorPrimary,
            R.color.colorPrimaryDark, R.color.colorRed, R.color.colorTheme, R.color.colorWathet,
            R.color.colorWhite, R.color.colorGray, R.color.colorYellow, R.color.colorOrange};
    private Calendar calendar;
    private int Year, Month, DayOfMonth, Position;
    private String content = "", eType = "支出";
    private RecordDao recordDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        mGv.setAdapter(new AccountGvAdapter(AccountActivity.this, "支出"));
        mTv_mine.setText(DayOfMonth + "");
        mTv_calender.setText(Month + "");
    }

    private void initListener() {
        mIv_back.setOnClickListener(this);
        mBtn_shouru.setOnClickListener(this);
        mBtn_zhichu.setOnClickListener(this);
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Position = position;
                mRl.setBackgroundResource(backgrounds[position]);
                mIv_item.setImageResource(zhichu_imgs[position]);
            }
        });
        mLl.setOnClickListener(this);
        mIv_mine.setOnClickListener(this);
        mTv_calender.setOnClickListener(this);
        mLl_ok.setOnClickListener(this);
    }

    private void initView() {
        mRl = (RelativeLayout) findViewById(R.id.account_rl);
        mIv_back = (ImageView) findViewById(R.id.account_iv_back);
        mIv_item = (ImageView) findViewById(R.id.account_iv_item);
        mBtn_zhichu = (Button) findViewById(R.id.account_btn_zhichu);
        mBtn_shouru = (Button) findViewById(R.id.account_btn_shouru);
        mEt_num = (EditText) findViewById(R.id.account_et_num);
        mGv = (GridView) findViewById(R.id.account_gv);
        mLl = (LinearLayout) findViewById(R.id.account_ll);
        mIv_mine = (ImageView) findViewById(R.id.account_iv_mine);
        mTv_mine = (TextView) findViewById(R.id.account_tv_mine);
        mTv_calender = (TextView) findViewById(R.id.account_tv_mine2);
        mLl_ok = (LinearLayout) findViewById(R.id.account_ll_ok);
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH) + 1;
        DayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        recordDao = new RecordDao(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_iv_back:
                finish();
                break;
            case R.id.account_btn_zhichu:
                mRl.setBackgroundResource(backgrounds[0]);
                mIv_item.setImageResource(zhichu_imgs[0]);
                mGv.setAdapter(new AccountGvAdapter(AccountActivity.this, "支出"));
                break;
            case R.id.account_btn_shouru:
                mRl.setBackgroundResource(backgrounds[0]);
                mIv_item.setImageResource(shouru_imgs[0]);
                mGv.setAdapter(new AccountGvAdapter(AccountActivity.this, "收入"));
                eType = "收入";
                break;
            case R.id.account_ll: {
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
                View view = View.inflate(AccountActivity.this, R.layout.dialog_zhanghuleixing, null);
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
                final ImageView ll_iv = (ImageView) AccountActivity.this.findViewById(R.id.account_ll_iv);
                final TextView ll_tv = (TextView) AccountActivity.this.findViewById(R.id.account_ll_tv);
                ll_cash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        ll_iv.setImageResource(R.drawable.ft_cash1);
                        ll_tv.setText("现金");
                    }
                });
                ll_chuxuka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        ll_iv.setImageResource(R.drawable.ft_chuxuka1);
                        ll_tv.setText("储蓄卡");
                    }
                });
                ll_creditcard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        ll_iv.setImageResource(R.drawable.ft_creditcard1);
                        ll_tv.setText("信用卡");
                    }
                });
                ll_wangluozhifu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        ll_iv.setImageResource(R.drawable.ft_wangluochongzhi1);
                        ll_tv.setText("支付宝");
                    }
                });
            }
            break;
            case R.id.account_iv_mine: {
                AlertDialog.Builder builder = new AlertDialog.Builder(AccountActivity.this);
                View view = View.inflate(AccountActivity.this, R.layout.dialog_zidingyishiwu, null);
                final EditText zidingyi_et = (EditText) view.findViewById(R.id.zidingyi_et);
                builder.setIcon(R.drawable.ic_launcher).setTitle("自定义事务").setView(view).
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                zidingyi_et.getText().toString();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
            break;
            case R.id.account_tv_mine2: {
                new DatePickerDialog(AccountActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Year = year;
                        Month = month + 1;
                        DayOfMonth = dayOfMonth;
                        mTv_calender.setText(Month + "");
                        mTv_mine.setText(DayOfMonth + "");
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
            break;
            case R.id.account_ll_ok:
                if (("").equals(mEt_num.getText().toString())
                        || ("0").equals(mEt_num.getText().toString())) {
                    ToastUtils.show(AccountActivity.this, "没花钱？");
                } else {
                    /**
                     * 返回
                     */
                    calendar.set(Calendar.YEAR, Year);
                    calendar.set(Calendar.MONTH, Month);
                    calendar.set(Calendar.DAY_OF_MONTH, DayOfMonth);
                    Date date = calendar.getTime();
                    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
                    String time=format.format(date);
                    float money = Float.parseFloat((mEt_num.getText().toString()));
                    int img = 0;
                    UserDao userdao = new UserDao(AccountActivity.this);
                    SharePreferenceUtils spUtils = new SharePreferenceUtils(AccountActivity.this);
                    int user_id = userdao.findIdByName(spUtils.getName());
                    if ("支出".equals(eType)) {
                        content = zhichu_strs[Position];
                        img = zhichu_imgs[Position];
                    } else if ("收入".equals(eType)) {
                        content = shouru_strs[Position];
                        img = shouru_imgs[Position];
                    }
                    recordDao.insertRecord(new AccountRecord(eType, time, content, money, img, user_id));
                    try {
                        List<AccountRecord> records = recordDao.queryAllRecord();
                        Log.i("msg", records.size() + "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finish();
                }
                break;
        }
    }
}
