package com.briup.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.briup.activity.AccountActivity;
import com.briup.activity.R;

/**
 * Created by vonym on 17-1-10.
 */

public class AccountGvAdapter extends BaseAdapter {
    private Context context;
    private String type,content;
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

    public AccountGvAdapter(Context context, String type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public int getCount() {
        return zhichu_imgs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.account_gv_item, null);
            holder = new viewHolder();
            holder.gv_iv = (ImageView) convertView.findViewById(R.id.account_gv_iv);
            holder.gv_tv = (TextView) convertView.findViewById(R.id.account_gv_tv);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        if ("支出".equals(type)) {
            holder.gv_iv.setImageResource(zhichu_imgs[position]);
            content=zhichu_strs[position];
            holder.gv_tv.setText(content);
        } else if ("收入".equals(type)) {
            holder.gv_iv.setImageResource(shouru_imgs[position]);
            content=shouru_strs[position];
            holder.gv_tv.setText(content);
        }
        return convertView;
    }

    static class viewHolder {
        private LinearLayout gv_ll;
        private ImageView gv_iv;
        private TextView gv_tv;
    }
}
