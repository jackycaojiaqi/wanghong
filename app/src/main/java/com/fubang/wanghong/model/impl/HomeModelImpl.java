package com.fubang.wanghong.model.impl;

import com.fubang.wanghong.entities.HomeEntity;
import com.fubang.wanghong.model.BaseModel;
import com.fubang.wanghong.model.HomeModel;
import com.fubang.wanghong.utils.ParamsMap;

import retrofit2.Callback;

/**
 * Created by dell on 2016/4/5.
 */
public class HomeModelImpl extends BaseModel implements HomeModel {
    private static volatile HomeModelImpl instance = null;

    private HomeModelImpl(){

    }

    public static HomeModelImpl getInstance() {
        if (instance == null) {
            synchronized (HomeModelImpl.class) {
                if (instance == null) {
                    instance = new HomeModelImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void getHomeData(Callback<HomeEntity> callback) {
        ParamsMap map = ParamsMap.getInstance();
        service.getHomeEntity(map).enqueue(callback);
    }
}
