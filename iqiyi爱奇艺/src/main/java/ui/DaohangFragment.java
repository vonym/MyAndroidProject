package ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.iqiyi.R;

public class DaohangFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final int[] grid1_imgs = {R.drawable.yule, R.drawable.fengyunbang, R.drawable.tiyu,
                R.drawable.zhibozhongxin, R.drawable.zixun, R.drawable.quanwangyingshi};
        final String[] grid1_txts = {"娱乐", "风云榜", "体育", "直播中心", "资讯", "全网影视"};
        final int[] grid2_imgs = {R.drawable.dianying, R.drawable.dianshiju, R.drawable.pianhua,
                R.drawable.zongyi, R.drawable.weidianying, R.drawable.quanwangyingshi};
        final String[] grid2_txts = {"电影", "电视剧", "片花", "综艺", "微电影", "脱口秀"};
        final int[] grid3_imgs = {R.drawable.dianying, R.drawable.dianshiju, R.drawable.pianhua,
                R.drawable.zongyi, R.drawable.weidianying};
        final String[] grid3_txts = {"动漫", "生活", "少儿", "母婴", "游戏"};
        View DaohangLayout = inflater.inflate(R.layout.daohangfragment, container, false);
        GridView grid1 = (GridView) DaohangLayout.findViewById(R.id.grid1);
        GridView grid2 = (GridView) DaohangLayout.findViewById(R.id.grid2);
        GridView grid3 = (GridView) DaohangLayout.findViewById(R.id.grid3);
        grid1.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return grid1_imgs.length;
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
                View view = View.inflate(getContext(), R.layout.daohang_style, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.daohang_img);
                TextView textView = (TextView) view.findViewById(R.id.daohang_txt);
                imageView.setImageResource(grid1_imgs[position]);
                textView.setText(grid1_txts[position]);
                return view;
            }
        });
        grid2.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return grid2_imgs.length;
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
                View view = View.inflate(getContext(), R.layout.daohang_style, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.daohang_img);
                TextView textView = (TextView) view.findViewById(R.id.daohang_txt);
                imageView.setImageResource(grid2_imgs[position]);
                textView.setText(grid2_txts[position]);
                return view;
            }
        });
        grid3.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return grid3_imgs.length;
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
                View view = View.inflate(getContext(), R.layout.daohang_style, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.daohang_img);
                TextView textView = (TextView) view.findViewById(R.id.daohang_txt);
                imageView.setImageResource(grid3_imgs[position]);
                textView.setText(grid3_txts[position]);
                return view;
            }
        });
        return DaohangLayout;
    }
}
