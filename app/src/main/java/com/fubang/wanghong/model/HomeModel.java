package com.fubang.wanghong.model;

import com.fubang.wanghong.entities.HomeEntity;

import retrofit2.Callback;

/**
 * Created by dell on 2016/4/5.
 */
public interface HomeModel {
    void getHomeData(Callback<HomeEntity> callback);
}
