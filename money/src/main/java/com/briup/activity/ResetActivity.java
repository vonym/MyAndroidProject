package com.briup.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.briup.db.UserDao;
import com.briup.utils.SharePreferenceUtils;
import com.briup.utils.ToastUtils;

/**
 * Created by vonym on 17-1-5.
 */
public class ResetActivity extends Activity implements View.OnClickListener {
    private ImageView mIv_back;
    private EditText mEt_name, mEt_psw, mEt_surePsw;
    private Button mBtn_reset, mBtn_clear;
    private UserDao userDao;
    private SharePreferenceUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        initView();
        initListener();
    }

    private void initView() {
        mIv_back = (ImageView) findViewById(R.id.reset_iv_back);
        mEt_name = (EditText) findViewById(R.id.reset_et_name);
        mEt_psw = (EditText) findViewById(R.id.reset_et_psw);
        mEt_surePsw = (EditText) findViewById(R.id.reset_et_surePsw);
        mBtn_reset = (Button) findViewById(R.id.reset_btn_reset);
        mBtn_clear = (Button) findViewById(R.id.reset_btn_clear);
        userDao = new UserDao(ResetActivity.this);
        spUtils=new SharePreferenceUtils(this);
    }

    private void initListener() {
        mIv_back.setOnClickListener(this);
        mBtn_reset.setOnClickListener(this);
        mBtn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_iv_back:
                finish();
                break;
            case R.id.reset_btn_reset:
                String name = mEt_name.getText().toString();
                String psw = mEt_psw.getText().toString();
                String surePsw = mEt_surePsw.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.show(ResetActivity.this, "用户名不能为空");
                } else if (TextUtils.isEmpty(psw) || TextUtils.isEmpty(surePsw)) {
                    ToastUtils.show(ResetActivity.this, "密码或者确认密码不能为空");
                } else if (!psw.equals(surePsw)) {
                    ToastUtils.show(ResetActivity.this, "密码与确认密码不一致");
                } else {
                    /**
                     * 数据库更新
                     */
                    userDao.updatePsw(name, psw);
                    spUtils.put("name",name);
                    spUtils.put("psw",psw);
                    finish();
                }
                break;
            case R.id.reset_btn_clear:
                mEt_name.setText("");
                mEt_psw.setText("");
                mEt_surePsw.setText("");
                break;
        }
    }
}
