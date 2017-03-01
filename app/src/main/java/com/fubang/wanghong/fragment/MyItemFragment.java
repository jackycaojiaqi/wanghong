package com.fubang.wanghong.fragment;


import android.support.v4.app.Fragment;

import com.fubang.wanghong.AppConstant;
import com.fubang.wanghong.R;

import org.androidannotations.annotations.EFragment;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_my_item)
public class MyItemFragment extends BaseFragment {

    private String type;
    @Override
    public void before() {
        type = getArguments().getString(AppConstant.HOME_TYPE);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
