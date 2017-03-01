package com.fubang.wanghong.model;

import com.fubang.wanghong.entities.HomeIconEntity;

import retrofit2.Callback;

/**
 * Created by dell on 2016/4/5.
 */
public interface HomeIconModel {
    void getHomeIconData(Callback<HomeIconEntity> callback);
}
