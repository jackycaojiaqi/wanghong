package com.fubang.wanghong.model;

import com.fubang.wanghong.entities.HomeHeadPicEntity;

import retrofit2.Callback;

/**
 * Created by dell on 2016/4/5.
 */
public interface HomeHeadPicModel {
    void getHeadPicData(Callback<HomeHeadPicEntity> callback);
}
