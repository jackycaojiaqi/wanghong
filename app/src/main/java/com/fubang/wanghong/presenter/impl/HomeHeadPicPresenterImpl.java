package com.fubang.wanghong.presenter.impl;

import com.fubang.wanghong.entities.HomeEntity;
import com.fubang.wanghong.entities.HomeHeadPicEntity;
import com.fubang.wanghong.entities.HomeIconEntity;
import com.fubang.wanghong.model.ModelFactory;
import com.fubang.wanghong.presenter.HomePresenter;
import com.fubang.wanghong.view.HomeHeadPicView;
import com.fubang.wanghong.view.HomeIconView;
import com.fubang.wanghong.view.HomeView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dell on 2016/4/5.
 */
public class HomeHeadPicPresenterImpl implements HomePresenter {
    private HomeHeadPicView homeHeadPicView;
    private HomeIconView homeIconView;
    private HomeView homeView;

    public HomeHeadPicPresenterImpl(HomeHeadPicView homeHeadPicView, HomeIconView homeIconView, HomeView homeView) {
        this.homeHeadPicView = homeHeadPicView;
        this.homeIconView = homeIconView;
        this.homeView = homeView;
    }

    @Override
    public void getHomeHeadPic() {
        ModelFactory.getInstance().getHomeHeadPicModelImpl().getHeadPicData(new Callback<HomeHeadPicEntity>() {
            @Override
            public void onResponse(Call<HomeHeadPicEntity> call, Response<HomeHeadPicEntity> response) {
                homeHeadPicView.successHeadPic(response.body());
            }

            @Override
            public void onFailure(Call<HomeHeadPicEntity> call, Throwable t) {
                homeHeadPicView.failedHeadPic();
            }
        });
    }

    @Override
    public void getHomeIcon() {
        ModelFactory.getInstance().getHomeIconModelImpl().getHomeIconData(new Callback<HomeIconEntity>() {
            @Override
            public void onResponse(Call<HomeIconEntity> call, Response<HomeIconEntity> response) {
                homeIconView.successIcon(response.body());
            }

            @Override
            public void onFailure(Call<HomeIconEntity> call, Throwable t) {
                homeIconView.failedIcon();
            }
        });
    }

    @Override
    public void getHome() {
        ModelFactory.getInstance().getHomeModelImpl().getHomeData(new Callback<HomeEntity>() {
            @Override
            public void onResponse(Call<HomeEntity> call, Response<HomeEntity> response) {
                homeView.successHome(response.body());
            }

            @Override
            public void onFailure(Call<HomeEntity> call, Throwable t) {
                homeView.failedHome();
            }
        });
    }
}
