package com.briup.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.briup.bean.AccountCard;
import com.briup.db.CardDao;
import com.briup.db.UserDao;
import com.briup.utils.SharePreferenceUtils;

import java.util.List;

/**
 * Created by vonym on 17-1-11.
 */
public class AddCardActivity extends Activity implements View.OnClickListener {
    private ImageView mIv_back, mIv_name, mIv_color;
    private EditText mEt_name, mEt_price;
    private RelativeLayout mRl_type, mRl_color;
    private TextView mTv_name;
    private Button mBtn;
    private String name = "现金";
    private float price = 0.0f;
    private int img = R.drawable.ft_cash, user_id = 0, backgroundColor = R.color.colorAccent;
    private CardDao cardDao;
    private UserDao userDao;
    SharePreferenceUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListener();
    }

    private void initListener() {
        mIv_back.setOnClickListener(this);
        mRl_type.setOnClickListener(this);
        mRl_color.setOnClickListener(this);
        mBtn.setOnClickListener(this);
    }

    private void initView() {
        cardDao = new CardDao(this);
        userDao = new UserDao(this);
        spUtils = new SharePreferenceUtils(this);
        mIv_back = (ImageView) findViewById(R.id.addCard_iv_back);
        mIv_name = (ImageView) findViewById(R.id.addCard_iv_name);
        mIv_color = (ImageView) findViewById(R.id.addCard_iv_color);
        mEt_name = (EditText) findViewById(R.id.addCard_et_name);
        mEt_price = (EditText) findViewById(R.id.addCard_et_price);
        mRl_type = (RelativeLayout) findViewById(R.id.addCard_rl_type);
        mRl_color = (RelativeLayout) findViewById(R.id.addCard_rl_color);
        mTv_name = (TextView) findViewById(R.id.addCard_tv_name);
        mBtn = (Button) findViewById(R.id.addCard_btn);
        backgroundColor = R.color.colorAccent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addCard_iv_back:
                finish();
                break;
            case R.id.addCard_rl_type:
                AlertDialog.Builder builder = new AlertDialog.Builder(AddCardActivity.this);
                View view = View.inflate(AddCardActivity.this, R.layout.dialog_zhanghuleixing1, null);
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
                LinearLayout ll_cash = (LinearLayout) view.findViewById(R.id.zhanghuleixing1_ll_cash);
                LinearLayout ll_chuxuka = (LinearLayout) view.findViewById(R.id.zhanghuleixing1_ll_chuxuka);
                LinearLayout ll_creditcard = (LinearLayout) view.findViewById(R.id.zhanghuleixing1_ll_creditcard);
                LinearLayout ll_wangluozhifu = (LinearLayout) view.findViewById(R.id.zhanghuleixing1_ll_wangluozhifu);
                LinearLayout ll_shiwuka = (LinearLayout) view.findViewById(R.id.zhanghuleixing1_ll_shiwuka);
                LinearLayout ll_yingshouqian = (LinearLayout) view.findViewById(R.id.zhanghuleixing1_ll_yingshouqian);
                ll_cash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img = R.drawable.ft_cash;
                        mIv_name.setImageResource(R.drawable.ft_cash1);
                        name = "现金";
                        mTv_name.setText(name);
                        dialog.dismiss();
                    }
                });
                ll_chuxuka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img = R.drawable.ft_chuxuka;
                        mIv_name.setImageResource(R.drawable.ft_chuxuka1);
                        name = "储蓄卡";
                        mTv_name.setText(name);
                        dialog.dismiss();
                    }
                });
                ll_creditcard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img = R.drawable.ft_creditcard;
                        mIv_name.setImageResource(R.drawable.ft_creditcard1);
                        name = "信用卡";
                        mTv_name.setText(name);
                        dialog.dismiss();
                    }
                });
                ll_wangluozhifu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img = R.drawable.ft_wangluochongzhi;
                        mIv_name.setImageResource(R.drawable.ft_wangluochongzhi1);
                        name = "支付宝";
                        mTv_name.setText(name);
                        dialog.dismiss();
                    }
                });
                ll_shiwuka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img = R.drawable.ft_shiwuka;
                        mIv_name.setImageResource(R.drawable.ft_shiwuka1);
                        name = "购物卡";
                        mTv_name.setText(name);
                        dialog.dismiss();
                    }
                });
                ll_yingshouqian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        img = R.drawable.ft_yingshouqian;
                        mIv_name.setImageResource(R.drawable.ft_yingshouqian1);
                        name = "应用收款";
                        mTv_name.setText(name);
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.addCard_rl_color:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(AddCardActivity.this);
                View view1 = View.inflate(AddCardActivity.this, R.layout.dialog_selectcolor, null);
                RadioGroup rg = (RadioGroup) view1.findViewById(R.id.addCard_rg);
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.radio1:
                                backgroundColor = R.color.colorAccent;
                                break;
                            case R.id.radio2:
                                backgroundColor = R.color.colorPrimaryDark;
                                break;
                            case R.id.radio3:
                                backgroundColor = R.color.colorWathet;
                                break;
                            case R.id.radio4:
                                backgroundColor = R.color.colorTheme;
                                break;
                            case R.id.radio5:
                                backgroundColor = R.color.colorRed;
                                break;
                            case R.id.radio6:
                                backgroundColor = R.color.colorYellow;
                                break;
                            default:
                                backgroundColor = R.color.colorAccent;
                                break;
                        }
                    }
                });
                builder1.setIcon(R.drawable.ic_launcher).setTitle("选择账户颜色").setView(view1).
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mIv_color.setImageResource(backgroundColor);
                    }
                });
                final AlertDialog dialog1 = builder1.create();
                dialog1.show();
                break;
            case R.id.addCard_btn:
                price = Float.parseFloat(mEt_price.getText().toString());
                user_id = userDao.findIdByName(spUtils.getName());
                cardDao.insertCard(new AccountCard(name, price, img, backgroundColor, user_id));
                finish();
                break;
        }
    }
}
