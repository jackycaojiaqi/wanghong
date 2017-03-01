package com.fubang.wanghong.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;

import com.fubang.wanghong.R;
import com.fubang.wanghong.ui.RechargeActivity_;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_vip)
public class VipFragment extends BaseFragment {
    @ViewById(R.id.vip_recharge_btn)
    CardView rechargeBtn;
    @Override
    public void initView() {
        rechargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RechargeActivity_.intent(getContext()).get());
            }
        });
    }

    @Override
    public void initData() {

    }
}
