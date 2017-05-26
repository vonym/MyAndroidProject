package com.example.listview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> list;

    public MyAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*getView的执行时机，首次看到几个item，那就执行几次*/
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        /*逗比式；完全没有任何优化
        //View.inflate这个方法是一个非常消耗资源的方法，并且每一个item的布局方式都是一样的
        View view1 = View.inflate(context, R.layout.mylistview, null);
        TextView textView = (TextView) view1.findViewById(R.id.txt);
        textView.setText(list.get(position) + "");*/

        /*普通式；虽然已经进行优化，但是优化不彻底
        if (view==null){
            view=View.inflate(context,R.layout.mylistview,null);
        }
        TextView textView=(TextView)view.findViewById(R.id.txt);
        textView.setText(list.get(position)+"");*/

        //文艺式
        ViewHolder viewHolder;
        if (view == null) {
            Log.v("msg", position + "" + view);
            view = View.inflate(context, R.layout.mylistview, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.txt);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(list.get(position) + "");
        return view;
    }

    //起到缓存作用的类
    static class ViewHolder {
        TextView textView;
    }
}
