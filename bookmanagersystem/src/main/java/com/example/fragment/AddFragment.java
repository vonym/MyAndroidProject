package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bean.Book;
import com.example.bookmanagersystem.R;
import com.example.db.BookDao;
import com.example.utils.ToastUtils;

/**
 * Created by vonym on 17-1-3.
 */

public class AddFragment extends Fragment {
    private EditText mEt_name, mEt_price;
    private BookDao dao;
    private Button mBtn_sure;

    /**
     * @param inflater:讲布局文件转换为view
     * @param container:父容器
     * @param savedInstanceState：是否与父容器...默认为false
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        initView(view);
        initListener();
        return view;
    }

    public void initView(View view) {
        mEt_name = (EditText) view.findViewById(R.id.add_et_bookName);
        mEt_price = (EditText) view.findViewById(R.id.add_et_bookPrice);
        mBtn_sure = (Button) view.findViewById(R.id.add_btn_sure);
        dao = new BookDao(getContext());
    }

    public void initListener() {
        mBtn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEt_name.getText().toString();
                String price = mEt_price.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showToast(getActivity(), "书名不能为空");
                } else if (TextUtils.isEmpty(price)) {
                    ToastUtils.showToast(getActivity(), "价格不能为空");
                } else if (dao.isExist(name)) {
                    ToastUtils.showToast(getActivity(), "该书已存在");
                } else {
                    dao.addBook(new Book(name, Double.valueOf(price)));
                    ToastUtils.showToast(getActivity(), "添加成功");
                }
                mEt_name.setText("");
                mEt_price.setText("");
            }
        });
    }



    /*//fragment 中不能使用onclick属性
    public void sure(View view) {
        String name = mEt_name.getText().toString();
        String price = mEt_price.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showToast(getActivity(), "书名不能为空");
        } else if (TextUtils.isEmpty(price)) {
            ToastUtils.showToast(getActivity(), "价格不能为空");
        } else {
            dao.addBook(new Book(name, Double.valueOf(price)));
            ToastUtils.showToast(getActivity(), "添加成功");
        }
    }*/
}
