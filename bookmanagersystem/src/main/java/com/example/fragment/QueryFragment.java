package com.example.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.adapter.QueryLvAdapter;
import com.example.bean.Book;
import com.example.bookmanagersystem.R;
import com.example.db.BookDao;
import com.example.db.SqliteHelper;
import com.example.utils.ToastUtils;

import java.util.List;

/**
 * Created by vonym on 17-1-3.
 */

public class QueryFragment extends Fragment {
    private EditText mEt_name;
    private Button mBtn_sure;
    private ListView mLv_show;
    private BookDao dao;
    private List<Book> books, books2;
    private QueryLvAdapter adapter;

    /**
     * @param inflater:讲布局文件转换为view
     * @param container:父容器
     * @param savedInstanceState：是否与父容器...默认为false
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        initView(view);
        initData();
        initAdapter();
        initListener();
        return view;
    }

    private void initAdapter() {
        mLv_show.setAdapter(new QueryLvAdapter(books, getActivity()));
    }

    private void initData() {
        books = dao.findAllBook();
    }

    private void initListener() {
        mBtn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEt_name.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    books2 = dao.findAllBook();
                    ToastUtils.showToast(getActivity(), "书名不能为空");
                } else if (!dao.isExist(name)) {
                    ToastUtils.showToast(getActivity(), "该书不存在");
                } else {
                    books2 = dao.findBookByName(name);
                /*adapter.notifyDataSetChanged();*/
                }
                mLv_show.setAdapter(new QueryLvAdapter(books2, getActivity()));
                mEt_name.setText("");
            }
        });
    }

    private void initView(View view) {
        mEt_name = (EditText) view.findViewById(R.id.query_et_name);
        mBtn_sure = (Button) view.findViewById(R.id.query_btn_sure);
        mLv_show = (ListView) view.findViewById(R.id.query_lv);
        dao = new BookDao(getActivity());
    }
}
