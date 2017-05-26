package com.briup.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.briup.bean.User;
import com.briup.db.UserDao;
import com.briup.utils.SharePreferenceUtils;
import com.briup.utils.ToastUtils;

/**
 * Created by vonym on 17-1-5.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText mEt_name, mEt_psw;
    private Button mBtn_login, mBtn_register;
    private ImageView mIv_back;
    private TextView mTv_foundPsw;
    private UserDao userDao;
    private SharePreferenceUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }

    private void initListener() {
        mIv_back.setOnClickListener(this);
        mBtn_login.setOnClickListener(this);
        mBtn_register.setOnClickListener(this);
        mTv_foundPsw.setOnClickListener(this);
    }

    private void initView() {
        mIv_back = (ImageView) findViewById(R.id.login_iv_back);
        mEt_name = (EditText) findViewById(R.id.login_et_name);
        mEt_psw = (EditText) findViewById(R.id.login_et_psw);
        mBtn_login = (Button) findViewById(R.id.login_btn_login);
        mBtn_register = (Button) findViewById(R.id.login_btn_register);
        mTv_foundPsw = (TextView) findViewById(R.id.login_tv_foundPsw);
        userDao = new UserDao(LoginActivity.this);
        spUtils = new SharePreferenceUtils(LoginActivity.this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.login_iv_back:
                finish();
                break;
            case R.id.login_btn_login:
                String name = mEt_name.getText().toString();
                String psw = mEt_psw.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psw)) {
                    ToastUtils.show(LoginActivity.this, "用户名或密码不能为空");
                } else {
                    if (userDao.isPswTrue(name, psw)) {
                        spUtils.put("name", name);
                        spUtils.put("psw", psw);
                        spUtils.setLogin(true);
                        ToastUtils.show(LoginActivity.this, "登录成功");
                        mEt_name.setText("");
                        mEt_psw.setText("");
                        finish();
                    } else {
                        mEt_name.setText("");
                        mEt_psw.setText("");
                        ToastUtils.show(LoginActivity.this, "用户名或密码错误");
                    }
                }
                break;
            case R.id.login_btn_register: {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.login_tv_foundPsw: {
                intent = new Intent(LoginActivity.this, ResetActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!spUtils.getName().equals("")) {
            mEt_name.setText(spUtils.getName());
        }
    }
}
