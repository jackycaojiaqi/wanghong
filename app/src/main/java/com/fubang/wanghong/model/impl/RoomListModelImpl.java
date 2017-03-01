package com.fubang.wanghong.model.impl;

import com.fubang.wanghong.AppConstant;
import com.fubang.wanghong.entities.RoomEntity;
import com.fubang.wanghong.model.BaseModel;
import com.fubang.wanghong.model.RoomListModel;
import com.fubang.wanghong.utils.ParamsMap;

import retrofit2.Callback;

/**
 * Created by dell on 2016/4/7.
 */
public class RoomListModelImpl extends BaseModel implements RoomListModel {
    private static volatile RoomListModelImpl instance = null;

    private RoomListModelImpl(){

    }

    public static RoomListModelImpl getInstance() {
        if (instance == null) {
            synchronized (RoomListModelImpl.class) {
                if (instance == null) {
                    instance = new RoomListModelImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public void getRoomListData(Callback<RoomEntity> callback, int count, int page, int groupId) {
        ParamsMap map = ParamsMap.getInstance();
        map.put(AppConstant.COUNT,count);
        map.put(AppConstant.PAGE,page);
        map.put(AppConstant.GROUP,groupId);
        service.getRoomEntity(map).enqueue(callback);
    }
}
