package com.briup.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.briup.activity.R;
import com.briup.utils.SharePreferenceUtils;

/**
 * Created by vonym on 17-1-4.
 */

public class formFragment extends Fragment {
    private SharePreferenceUtils spUtils;
    private boolean isLogin = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spUtils = new SharePreferenceUtils(getActivity());
        isLogin = spUtils.getLogin();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, null, false);
        if (isLogin) {

        } else {
        }
        return view;
    }
}
