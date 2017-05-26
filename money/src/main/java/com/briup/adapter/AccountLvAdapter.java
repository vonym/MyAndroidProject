package com.briup.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.briup.activity.R;
import com.briup.bean.AccountRecord;
import com.briup.db.RecordDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vonym on 17-1-10.
 */

public class AccountLvAdapter extends BaseAdapter {
    private Context context;
    private RecordDao recordDao;
    private List<AccountRecord> records;

    public AccountLvAdapter(Context context) {
        this.context = context;
        recordDao = new RecordDao(context);
        try {
            records = new ArrayList<>();
            records = recordDao.queryAllRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return records.size();
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
            convertView = View.inflate(context, R.layout.account_lv_item, null);
            holder = new viewHolder();
            holder.lv_tv_left = (TextView) convertView.findViewById(R.id.account_lv_item_tv_left);
            holder.lv_tv_right = (TextView) convertView.findViewById(R.id.account_lv_item_tv_right);
            holder.lv_iv = (ImageView) convertView.findViewById(R.id.account_lv_item_iv);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        String type = records.get(position).getEtype();
        String time = records.get(position).getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(time);
            SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
            SimpleDateFormat MM = new SimpleDateFormat("MM");
            SimpleDateFormat dd = new SimpleDateFormat("dd");
            String str_yyyy = yyyy.format(date);
            String content = records.get(position).getContent();
            double money = records.get(position).getMoney();
            int img = records.get(position).getImg();
            if (("支出").equals(type)) {
                holder.lv_tv_left.setText(dd.format(date) + "日");
                holder.lv_tv_right.setText(content + "  -" + money);
            } else if (("收入").equals(type)) {
                holder.lv_tv_left.setText(content + "   +" + money);
                holder.lv_tv_right.setText(dd.format(date) + "日");
            }
            holder.lv_iv.setImageResource(img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    static class viewHolder {
        private TextView lv_tv_left, lv_tv_right;
        private ImageView lv_iv;
    }
}
