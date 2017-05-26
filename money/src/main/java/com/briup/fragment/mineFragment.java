package com.briup.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.briup.activity.LoginActivity;
import com.briup.activity.R;
import com.briup.activity.UserInfoActivity;
import com.briup.db.UserDao;
import com.briup.utils.SharePreferenceUtils;
import com.briup.utils.ToastUtils;

import static android.app.Activity.RESULT_OK;

/**
 * Created by vonym on 17-1-4.
 */

public class mineFragment extends Fragment {
    private ImageView mIv_header;
    private TextView mTv_name;
    private SharePreferenceUtils spUtils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null, false);
        initView(view);
        return view;
    }

    private void initListener() {
        mIv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (spUtils.getName().equals("")) {
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getActivity(), UserInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initView(View view) {
        mIv_header = (ImageView) view.findViewById(R.id.mine_iv_header);
        mTv_name = (TextView) view.findViewById(R.id.mine_tv_name);
        spUtils = new SharePreferenceUtils(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        initListener();
        if (spUtils.getName().equals("")) {
            mIv_header.setImageResource(R.drawable.mine_pic_nor);
            mTv_name.setText("请登录");
        } else {
            mTv_name.setText(spUtils.getName());
            mIv_header.setImageResource(R.drawable.login_mine_pic);
        }
    }
}
