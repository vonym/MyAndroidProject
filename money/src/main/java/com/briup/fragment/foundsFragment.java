package com.briup.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.briup.activity.AddCardActivity;
import com.briup.activity.R;
import com.briup.adapter.FoundsLvAdapter;
import com.briup.bean.AccountCard;
import com.briup.bean.AccountRecord;
import com.briup.db.CardDao;
import com.briup.db.RecordDao;
import com.briup.utils.SharePreferenceUtils;
import com.briup.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonym on 17-1-4.
 */

public class foundsFragment extends Fragment implements View.OnClickListener {
    private TextView mTv_jieyu;
    private SharePreferenceUtils spUtils;
    private LinearLayout mLl1, mLl_addCard;
    private Button mBtn_tranfer;
    private ListView mLv;
    private List<AccountCard> cards;
    private CardDao cardDao;
    private RecordDao recordDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spUtils = new SharePreferenceUtils(getActivity());
        cardDao = new CardDao(getActivity());
        recordDao = new RecordDao(getActivity());
        cards = new ArrayList<>();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_founds, null, false);
        initView(view);
        initListener();
        return view;
    }

    private void initData() {
        jieyu();
        mTv_jieyu.setText(jieyu() + "");
        mLv.setAdapter(new FoundsLvAdapter(getActivity()));
    }

    private int jieyu() {
        try {
            int price = 0;
            int zhichu = 0;
            int shouru = 0;
            for (AccountCard card : cardDao.queryAllCard()) {
                price += card.getPrice();
            }
            for (AccountRecord record : recordDao.queryAllRecord()) {
                if ("支出".equals(record.getEtype())) {
                    zhichu += record.getMoney();
                } else if ("收入".equals(record.getEtype())) {
                    shouru += record.getMoney();
                }
            }
            return price + shouru - zhichu;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!spUtils.getName().equals("")) {
            mLl1.setVisibility(View.VISIBLE);
            mBtn_tranfer.setVisibility(View.VISIBLE);
            mLv.setVisibility(View.VISIBLE);
            initData();
        }
    }

    private void initListener() {
        mBtn_tranfer.setOnClickListener(this);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = View.inflate(getActivity(), R.layout.dialog_updateprice, null);
                TextView tv = (TextView) view1.findViewById(R.id.updateprice_ll);
                final EditText et = (EditText) view1.findViewById(R.id.updateprice_et);
                cards = cardDao.queryAllCard();
                builder.setView(view1).setNegativeButton("取消", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!et.getText().toString().equals("")) {
                            cardDao.updateCardPrice(cards.get(position).getCardName(),
                                    Float.parseFloat(et.getText().toString()));
                            initData();
                        }
                    }
                }).create().show();
                tv.setText(cards.get(position).getCardName());
                tv.setBackgroundResource(cards.get(position).getColor());
            }
        });
        mLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.drawable.ic_launcher).setTitle("警告").setMessage("确定要删除这个资金账户吗")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cards = cardDao.queryAllCard();
                        cardDao.deleteCard(cards.get(position).getCardName());
                        initData();
                    }
                }).create().show();
                return true;
            }
        });
        mLl_addCard.setOnClickListener(this);
    }

    private void initView(View view) {
        mTv_jieyu = (TextView) view.findViewById(R.id.founds_tv_jieyu);
        mLl1 = (LinearLayout) view.findViewById(R.id.founds_ll1);
        mBtn_tranfer = (Button) view.findViewById(R.id.founds_btn_transfer);
        mLv = (ListView) view.findViewById(R.id.founds_lv);
        mLl_addCard = (LinearLayout) view.findViewById(R.id.founds_ll_addCard);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.founds_btn_transfer:
                break;
            case R.id.founds_ll_addCard:
                if (spUtils.getName().equals("")) {
                    ToastUtils.show(getActivity(), "你还没登录呢！");
                } else {
                    Intent intent = new Intent(getActivity(), AddCardActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
