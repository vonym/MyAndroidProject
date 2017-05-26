package com.briup.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.briup.activity.R;
import com.briup.bean.AccountCard;
import com.briup.db.CardDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonym on 17-1-11.
 */

public class FoundsLvAdapter extends BaseAdapter {
    private Context context;
    private CardDao cardDao;
    private List<AccountCard> cards;

    public FoundsLvAdapter(Context context) {
        this.context = context;
        cardDao = new CardDao(context);
        cards = new ArrayList<>();
        cards = cardDao.queryAllCard();
    }

    @Override
    public int getCount() {
        return cards.size();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.founds_lv_item, null);
            holder = new ViewHolder();
            holder.lv_rl = (RelativeLayout) convertView.findViewById(R.id.founds_lv_item_rl);
            holder.lv_iv = (ImageView) convertView.findViewById(R.id.founds_lv_item_iv);
            holder.lv_tv_name = (TextView) convertView.findViewById(R.id.founds_lv_item_tv_name);
            holder.lv_tv_price = (TextView) convertView.findViewById(R.id.founds_lv_item_tv_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /**
         * 查询所有的card
         */
        holder.lv_rl.setBackgroundResource(cards.get(position).getColor());
        holder.lv_iv.setImageResource(cards.get(position).getImg());
        holder.lv_tv_name.setText(cards.get(position).getCardName());
        holder.lv_tv_price.setText(cards.get(position).getPrice() + "");
        return convertView;
    }

    static class ViewHolder {
        private RelativeLayout lv_rl;
        private ImageView lv_iv;
        private TextView lv_tv_name, lv_tv_price;
    }
}
