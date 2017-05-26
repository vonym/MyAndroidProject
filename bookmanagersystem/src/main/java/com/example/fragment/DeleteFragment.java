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

import com.example.bookmanagersystem.R;
import com.example.db.BookDao;
import com.example.utils.ToastUtils;

/**
 * Created by vonym on 17-1-3.
 */

public class DeleteFragment extends Fragment {
    private EditText mEt_name;
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
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        initView(view);
        initListener();
        return view;
    }

    public void initListener() {
        mBtn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEt_name.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showToast(getActivity(), "书名不能为空");
                } else if (!dao.isExist(name)) {
                    ToastUtils.showToast(getActivity(), "该书不存在");
                } else {
                    dao.deleteBook(name);
                    ToastUtils.showToast(getActivity(), "删除成功");
                }
                mEt_name.setText("");
            }
        });
    }

    public void initView(View view) {
        mEt_name = (EditText) view.findViewById(R.id.delete_et_name);
        mBtn_sure = (Button) view.findViewById(R.id.delete_btn_sure);
        dao = new BookDao(getActivity());
    }
}
