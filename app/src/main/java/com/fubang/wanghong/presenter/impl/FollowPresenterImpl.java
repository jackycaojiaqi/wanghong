package com.fubang.wanghong.presenter.impl;

import com.fubang.wanghong.entities.FollowEntity;
import com.fubang.wanghong.model.ModelFactory;
import com.fubang.wanghong.presenter.FollowPresenter;
import com.fubang.wanghong.view.FollowView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　　┃　＞　　　＜　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃...　⌒　...　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃   神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┗━━━┓
 * 　　　　　　　　　┃　　　　　　　┣┓
 * 　　　　　　　　　┃　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * <p>
 * 项目名称：Youliao
 * 类描述：
 * 创建人：zhuyunjian
 * 创建时间：2016-11-24 15:19
 * 修改人：zhuyunjian
 * 修改时间：2016-11-24 15:19
 * 修改备注：
 */
public class FollowPresenterImpl implements FollowPresenter {
    private FollowView followView;
    private int userid;

    public FollowPresenterImpl(FollowView followView, int userid) {
        this.followView = followView;
        this.userid = userid;
    }

    @Override
    public void getFollowList() {
        ModelFactory.getInstance().getFollowModelImpl().getFollowData(new Callback<FollowEntity>() {
            @Override
            public void onResponse(Call<FollowEntity> call, Response<FollowEntity> response) {
                followView.successFollowList(response.body());
            }

            @Override
            public void onFailure(Call<FollowEntity> call, Throwable t) {
                followView.failFollowList();
            }
        },userid);
    }
}
