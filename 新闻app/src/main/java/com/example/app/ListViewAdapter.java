package com.example.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by vonym on 17-3-16.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<NewsBean.ResultBean.DataBean> dataBeanList;

    public ListViewAdapter(Context context, List<NewsBean.ResultBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.main_list_item, null);
            holder = new ViewHolder();
            holder.item_txt = (TextView) convertView.findViewById(R.id.list_item_txt);
            holder.item_img = (ImageView) convertView.findViewById(R.id.list_item_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item_txt.setText(dataBeanList.get(position).getTitle());
        ConnectionUtils.getBitmap(dataBeanList.get(position).getThumbnail_pic_s(), new HttpCallbackListener() {
            @Override
            public void onSuccess(Object response) {
                holder.item_img.setImageBitmap((Bitmap) response);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        return convertView;
    }

    static class ViewHolder {
        private TextView item_txt;
        private ImageView item_img;
    }
}