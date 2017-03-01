package com.fubang.wanghong.view;

import com.fubang.wanghong.entities.HomeEntity;

/**
 * Created by dell on 2016/4/5.
 */
public interface HomeView {
    void successHome(HomeEntity entity);
    void failedHome();
}
