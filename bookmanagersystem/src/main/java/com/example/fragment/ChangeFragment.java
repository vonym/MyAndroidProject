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

import com.example.bean.Book;
import com.example.bookmanagersystem.R;
import com.example.db.BookDao;
import com.example.utils.ToastUtils;

/**
 * Created by vonym on 17-1-3.
 */

public class ChangeFragment extends Fragment {
    private EditText mEt_oldName, mEt_newName, mEt_newPrice;
    private Button mBtn_sure;
    private BookDao dao;

    /**
     * @param inflater:讲布局文件转换为view
     * @param container:父容器
     * @param savedInstanceState：是否与父容器...默认为false
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change, container, false);
        initView(view);
        initListener();
        return view;
    }

    public void initListener() {
        mBtn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldName = mEt_oldName.getText().toString();
                String newName = mEt_newName.getText().toString();
                String newPrice = mEt_newPrice.getText().toString();
                if (TextUtils.isEmpty(oldName) || TextUtils.isEmpty(newName) || TextUtils.isEmpty(newPrice)) {
                    ToastUtils.showToast(getActivity(), "书名或价格不能为空");
                } else if (!dao.isExist(oldName)) {
                    ToastUtils.showToast(getActivity(), "该书不存在");
                } else {
                    dao.changeBook(oldName, new Book(newName, Double.valueOf(newPrice)));
                    ToastUtils.showToast(getActivity(), "修改成功");
                }
                mEt_oldName.setText("");
                mEt_newName.setText("");
                mEt_newPrice.setText("");
            }
        });
    }

    public void initView(View view) {
        mEt_oldName = (EditText) view.findViewById(R.id.change_et_oldName);
        mEt_newName = (EditText) view.findViewById(R.id.change_et_newName);
        mEt_newPrice = (EditText) view.findViewById(R.id.change_et_newPrice);
        mBtn_sure = (Button) view.findViewById(R.id.change_btn_sure);
        dao = new BookDao(getActivity());
    }
}
