package com.example.vonym.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by vonym on 16-12-9.
 */
public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> list;

    public NewsAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Map<String, Object> map = list.get(i);
        if (view == null) {
            view = View.inflate(context, R.layout.news_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.img);
            viewHolder.textView = (TextView) view.findViewById(R.id.txt);
            viewHolder.textView1 = (TextView) view.findViewById(R.id.txt1);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource((int) map.get("img"));
        viewHolder.textView.setText((String) map.get("title"));
        viewHolder.textView1.setText((String) map.get("doc"));
        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView textView1;
    }
}
