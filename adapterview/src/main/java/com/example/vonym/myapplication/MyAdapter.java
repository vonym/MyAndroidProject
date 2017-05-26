package com.example.vonym.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by vonym on 16-12-8.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private String[] names;

    public MyAdapter(Context context, String[] names) {
        this.context = context;
        this.names = names;
    }

    //控制当前的ListView中有几个列表项item
    @Override
    public int getCount() {
        return names.length;
    }

    //返回当前item所显示的数据
    @Override
    public Object getItem(int position) {
        return null;
    }

    //根据ListView的位置返回适配器的位置
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //用于返回每一个item的布局界面
    //position : 代表当前item的索引
    //
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
       /* 1.讲我们自己定义的布局文件加载到java代码中*/
        View view1 = View.inflate(context, R.layout.baseadapter_layoyt, null);
        TextView textView = (TextView) view1.findViewById(R.id.text);
        textView.setText(names[position]);
        return view1;
    }
}
