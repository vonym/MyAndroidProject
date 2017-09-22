package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonym on 2017/4/12 0012.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private int[] imgs = {R.mipmap.ic_launcher};
    private String[] txts = new String[20];

    public ListViewAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < 20; i++) {
            txts[i] = "测试     " + (i + 1);
        }
    }

    @Override
    public int getCount() {
        return txts.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.list_item, null);
            holder.item_img = (ImageView) convertView.findViewById(R.id.item_img);
            holder.item_txt = (TextView) convertView.findViewById(R.id.item_txt);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.item_img.setImageResource(imgs[0]);
        holder.item_txt.setText(txts[position]);
        return convertView;
    }

    static class ViewHolder {
        ImageView item_img;
        TextView item_txt;
    }
}
