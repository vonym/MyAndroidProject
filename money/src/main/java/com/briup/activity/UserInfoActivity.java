package com.briup.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.briup.db.UserDao;
import com.briup.utils.SharePreferenceUtils;
import com.briup.utils.ToastUtils;

/**
 * Created by vonym on 17-1-7.
 */

public class UserInfoActivity extends Activity implements View.OnClickListener {
    private SharePreferenceUtils spUtils;
    private UserDao userDao;
    private ImageView mIv_back;
    private RelativeLayout mRl_name, mRl_psw;
    private Button mBtn_exit;
    private View view_name, view_psw;
    private EditText mEt_name, mEt_psw, mEt_surePsw;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userifo);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        mTv.setText(spUtils.getName());
    }

    private void initView() {
        spUtils = new SharePreferenceUtils(this);
        userDao = new UserDao(this);
        mIv_back = (ImageView) findViewById(R.id.userinfo_iv_back);
        mRl_name = (RelativeLayout) findViewById(R.id.userinfo_rl_name);
        mRl_psw = (RelativeLayout) findViewById(R.id.userinfo_rl_psw);
        mBtn_exit = (Button) findViewById(R.id.userinfo_btn_exit);
        view_name = View.inflate(this, R.layout.dialog_userinfo_name, null);
        view_psw = View.inflate(UserInfoActivity.this, R.layout.dialog_userinfo_psw, null);
        mEt_name = (EditText) view_name.findViewById(R.id.dialog_name);
        mEt_psw = (EditText) view_psw.findViewById(R.id.dialog_psw);
        mEt_surePsw = (EditText) view_psw.findViewById(R.id.dialog_surepsw);
        mTv = (TextView) findViewById(R.id.userinfo_tv);
    }

    private void initListener() {
        mIv_back.setOnClickListener(this);
        mRl_name.setOnClickListener(this);
        mRl_psw.setOnClickListener(this);
        mBtn_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this);
        switch (v.getId()) {
            case R.id.userinfo_iv_back:
                UserInfoActivity.this.finish();
                break;
            case R.id.userinfo_rl_name:
                builder.setView(view_name).setIcon(R.drawable.ic_launcher).setTitle("修改昵称").
                        setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                userDao.updateName(spUtils.getName(), mEt_name.getText().toString());
                                spUtils.put("name", mEt_name.getText().toString());
                                mTv.setText(mEt_name.getText().toString());
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;
            case R.id.userinfo_rl_psw:
                builder.setView(view_psw).setIcon(R.drawable.ic_launcher).setTitle("修改密码")
                        .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (mEt_psw.getText().toString().equals(mEt_surePsw.getText().toString())) {
                                    userDao.updatePsw(spUtils.getName(), mEt_psw.getText().toString());
                                    spUtils.put("psw", mEt_psw.getText().toString());
                                } else {
                                    ToastUtils.show(UserInfoActivity.this, "密码不一致");
                                }
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;
            case R.id.userinfo_btn_exit:
                spUtils.put("name", "");
                spUtils.put("psw", "");
                UserInfoActivity.this.finish();
                break;
        }
    }
}
