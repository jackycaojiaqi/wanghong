package com.fubang.wanghong.model.impl;

import com.fubang.wanghong.entities.AnchorListEntity;
import com.fubang.wanghong.model.AnchorModel;
import com.fubang.wanghong.model.BaseModel;
import com.fubang.wanghong.utils.ParamsMap;

import retrofit2.Callback;

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
 * <p/>
 * 项目名称：MyApplication
 * 类描述：
 * 创建人：dell
 * 创建时间：2016-06-24 17:07
 * 修改人：dell
 * 修改时间：2016-06-24 17:07
 * 修改备注：
 */
public class AnchorModelImpl extends BaseModel implements AnchorModel {
    private static volatile AnchorModelImpl instance = null;

    private AnchorModelImpl(){

    }

    public static AnchorModelImpl getInstance() {
        if (instance == null) {
            synchronized (AnchorModelImpl.class) {
                if (instance == null) {
                    instance = new AnchorModelImpl();
                }
            }
        }
        return instance;
    }
    @Override
    public void getAnchorEntityData(Callback<AnchorListEntity> callback) {
        ParamsMap map = ParamsMap.getInstance();
        map.put("type",1);
        service.getAnchorEntity(1).enqueue(callback);
    }
}
