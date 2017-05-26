package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bean.FileInfo;

import com.example.app.R;
import com.example.service.DownLoadService;

public class DownLoadAdapter extends BaseAdapter {

    private Context context;
    private List<FileInfo> list;

    public DownLoadAdapter(Context context, List<FileInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder viewHolder;
        final FileInfo fileInfo = list.get(position);

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.myview, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.start_Btn = (Button) convertView
                    .findViewById(R.id.start);
            viewHolder.puase_Btn = (Button) convertView
                    .findViewById(R.id.pause);
            viewHolder.progressBar = (ProgressBar) convertView
                    .findViewById(R.id.pb);
            viewHolder.textView.setText(fileInfo.getFileName());
            viewHolder.progressBar.setMax(100);
            viewHolder.start_Btn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(context, DownLoadService.class);
                    intent.setAction(DownLoadService.ACTION_START);
                    intent.putExtra("fileinfo", fileInfo);
                    context.startService(intent);
                }
            });
            viewHolder.puase_Btn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent(context, DownLoadService.class);
                    intent.setAction(DownLoadService.ACTION_PUASE);
                    intent.putExtra("fileinfo", fileInfo);
                    context.startService(intent);
                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.progressBar.setProgress(fileInfo.getFinished());


        return convertView;
    }

    public void updateProgress(int id, int progress) {
        FileInfo fileInfo = list.get(id);
        fileInfo.setFinished(progress);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView textView;
        Button start_Btn, puase_Btn;
        ProgressBar progressBar;
    }

}
