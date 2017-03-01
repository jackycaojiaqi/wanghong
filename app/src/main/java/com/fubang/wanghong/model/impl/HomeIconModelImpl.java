package com.fubang.wanghong.model.impl;

import com.fubang.wanghong.entities.HomeIconEntity;
import com.fubang.wanghong.model.BaseModel;
import com.fubang.wanghong.model.HomeIconModel;
import com.fubang.wanghong.utils.ParamsMap;

import retrofit2.Callback;

/**
 * Created by dell on 2016/4/5.
 */
public class HomeIconModelImpl extends BaseModel implements HomeIconModel {
    private static volatile HomeIconModelImpl instance = null;

    private HomeIconModelImpl(){

    }

    public static HomeIconModelImpl getInstance() {
        if (instance == null) {
            synchronized (HomeIconModelImpl.class) {
                if (instance == null) {
                    instance = new HomeIconModelImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public void getHomeIconData(Callback<HomeIconEntity> callback) {
        ParamsMap map = ParamsMap.getInstance();
        service.getHomeIconEntity(map).enqueue(callback);
    }
}
