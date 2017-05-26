package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bean.Book;
import com.example.bookmanagersystem.R;

import java.util.List;

/**
 * Created by vonym on 17-1-3.
 */

public class QueryLvAdapter extends BaseAdapter {
    private List<Book> books;
    private Context context;

    public QueryLvAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    //首先执行这个方法，确定条目数量
    @Override
    public int getCount() {
        return books.size();
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
        View view = View.inflate(context, R.layout.query_lv_item, null);
        TextView tv_name = (TextView) view.findViewById(R.id.query_lv_item_tv_name);
        TextView tv_price = (TextView) view.findViewById(R.id.query_lv_item_tv_price);
        tv_name.setText((books.get(position)).getName());
        tv_price.setText((books.get(position).getPrice()) + "");
        return view;
    }
}
