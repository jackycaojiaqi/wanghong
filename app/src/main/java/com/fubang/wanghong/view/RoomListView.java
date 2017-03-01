package com.fubang.wanghong.view;

import com.fubang.wanghong.entities.RoomEntity;

/**
 * 显示房间列表的网络请求接口
 * Created by dell on 2016/4/7.
 */
public interface RoomListView {
    void successRoomList(RoomEntity entity);
    void faidedRoomList();
}
