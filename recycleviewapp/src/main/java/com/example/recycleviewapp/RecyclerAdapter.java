package com.example.recycleviewapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by vonym on 17-4-9.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Integer> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            imageView = (ImageView) view.findViewById(R.id.img);
            textView = (TextView) view.findViewById(R.id.txt);
        }
    }

    public RecyclerAdapter(List<Integer> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //具体的逻辑代码
                Toast.makeText(v.getContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //具体的逻辑代码
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.imageView.setImageResource(list.get(position));
        holder.imageView.setImageResource(R.mipmap.ic_launcher);
        holder.textView.setText(position + "");
    }

    @Override
    public int getItemCount() {
        return 50;
    }
}
