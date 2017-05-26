package com.briup.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.briup.activity.AccountActivity;
import com.briup.activity.AccountLvItemActivity;
import com.briup.activity.R;
import com.briup.adapter.AccountLvAdapter;
import com.briup.db.RecordDao;
import com.briup.utils.SharePreferenceUtils;
import com.briup.utils.ToastUtils;

import java.util.Calendar;

/**
 * Created by vonym on 17-1-4.
 */

public class accountFragment extends Fragment implements View.OnClickListener {
    private ImageView mIv_calender, mIv_pen, mIv_lvGone;
    private TextView mTv_calender, mTv_shuru, mTv_zhichu, mTv_shuruNum, mTv_zhichuNum;
    private ListView mLv;
    private SharePreferenceUtils spUtils;
    private Calendar calendar;
    private RecordDao recordDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spUtils = new SharePreferenceUtils(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, null, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initData() {
        mTv_calender.setText((calendar.get(Calendar.MONTH) + 1) + "");
    }

    private void initListener() {
        mIv_calender.setOnClickListener(this);
        mIv_pen.setOnClickListener(this);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), AccountLvItemActivity.class);
                intent.putExtra("position",position);
                startActivityForResult(intent, 1);
            }
        });
        mLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_launcher).
                        setMessage("确定删除这条记录吗").
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    recordDao.deleteRecord(recordDao.queryAllRecord().get(position).getContent());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                mLv.setAdapter(new AccountLvAdapter(getActivity()));
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                return true;
            }
        });
    }

    private void initView(View view) {
        calendar = Calendar.getInstance();
        recordDao = new RecordDao(getActivity());
        mIv_calender = (ImageView) view.findViewById(R.id.account_iv_calender);
        mIv_pen = (ImageView) view.findViewById(R.id.account_iv_pen);
        mIv_lvGone = (ImageView) view.findViewById(R.id.account_iv_lvgone);
        mTv_calender = (TextView) view.findViewById(R.id.account_tv_calender);
        mTv_shuru = (TextView) view.findViewById(R.id.account_tv_shouru);
        mTv_zhichu = (TextView) view.findViewById(R.id.account_tv_zhichu);
        mTv_shuruNum = (TextView) view.findViewById(R.id.account_tv_shouruNum);
        mTv_zhichuNum = (TextView) view.findViewById(R.id.account_tv_zhichuNum);
        mLv = (ListView) view.findViewById(R.id.account_lv);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (spUtils.getName().equals("")) {
            mLv.setVisibility(View.GONE);
            mIv_lvGone.setVisibility(View.VISIBLE);
        } else {
            mIv_lvGone.setVisibility(View.GONE);
            mLv.setVisibility(View.VISIBLE);
            mLv.setAdapter(new AccountLvAdapter(getActivity()));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_iv_calender:
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mTv_calender.setText((month + 1) + "");
                        mTv_shuru.setText((month + 1) + "月收入");
                        mTv_zhichu.setText((month + 1) + "月支出");
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
                mLv.setAdapter(new AccountLvAdapter(getActivity()));
                break;
            case R.id.account_iv_pen:
                if (spUtils.getName().equals("")) {
                    ToastUtils.show(getActivity(), "请登录");
                } else {
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
