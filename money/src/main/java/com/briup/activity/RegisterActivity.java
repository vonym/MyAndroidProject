package com.briup.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.briup.bean.AccountCard;
import com.briup.bean.User;
import com.briup.db.CardDao;
import com.briup.db.UserDao;
import com.briup.utils.SharePreferenceUtils;
import com.briup.utils.ToastUtils;

/**
 * Created by vonym on 17-1-5.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    private ImageView mIv_back;
    private EditText mEt_name, mEt_psw, mEt_surePsw;
    private Button mBtn_register, mBtn_reset;
    private UserDao userDao;
    private CardDao cardDao;
    private SharePreferenceUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initListener();
    }

    private void initView() {
        mIv_back = (ImageView) findViewById(R.id.register_iv_back);
        mEt_name = (EditText) findViewById(R.id.register_et_name);
        mEt_psw = (EditText) findViewById(R.id.register_et_psw);
        mEt_surePsw = (EditText) findViewById(R.id.register_et_surePsw);
        mBtn_register = (Button) findViewById(R.id.register_btn_register);
        mBtn_reset = (Button) findViewById(R.id.register_btn_reset);
        userDao = new UserDao(RegisterActivity.this);
        cardDao = new CardDao(RegisterActivity.this);
        spUtils = new SharePreferenceUtils(this);
    }

    private void initListener() {
        mIv_back.setOnClickListener(this);
        mBtn_reset.setOnClickListener(this);
        mBtn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_iv_back:
                finish();
                break;
            case R.id.register_btn_register:
                String name = mEt_name.getText().toString();
                String psw = mEt_psw.getText().toString();
                String surePsw = mEt_surePsw.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.show(RegisterActivity.this, "用户名不能为空");
                } else if (TextUtils.isEmpty(psw) || TextUtils.isEmpty(surePsw)) {
                    ToastUtils.show(RegisterActivity.this, "密码或者确认密码不能为空");
                } else if (!psw.equals(surePsw)) {
                    ToastUtils.show(RegisterActivity.this, "密码与确认密码不一致");
                } else {
                    /**
                     * 注册用户
                     */
                    userDao.insertUser(new User(name, psw));
                    spUtils.put("name", name);
                    cardDao.insertCard(new AccountCard("现金", 0.0f, R.drawable.ft_cash,
                            R.color.colorAccent, userDao.findIdByName(spUtils.getName())));
                    ToastUtils.show(RegisterActivity.this, "注册成功");
                    finish();
                }
                break;
            case R.id.register_btn_reset:
                mEt_name.setText("");
                mEt_psw.setText("");
                mEt_surePsw.setText("");
                break;
        }
    }
}
