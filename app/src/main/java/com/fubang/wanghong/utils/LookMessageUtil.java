package com.fubang.wanghong.utils;

import com.fubang.wanghong.R;
import com.fubang.wanghong.entities.LookMessageEntity;

import java.util.ArrayList;
import java.util.List;

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
 * <p>
 * 项目名称：Wanghong
 * 类描述：
 * 创建人：zhuyunjian
 * 创建时间：2017-01-03 15:24
 * 修改人：zhuyunjian
 * 修改时间：2017-01-03 15:24
 * 修改备注：
 */
public class LookMessageUtil {
    public static List<LookMessageEntity> getMessageEntity() {
        List<LookMessageEntity> list = new ArrayList<>();
        list.add(new LookMessageEntity(R.mipmap.tellqiaoqiao,"对TA悄悄说"));
        list.add(new LookMessageEntity(R.mipmap.tcroom,"踢出房间"));
        list.add(new LookMessageEntity(R.mipmap.notell,"禁言"));
        list.add(new LookMessageEntity(R.mipmap.yestell,"解除禁言"));
        return list;
    }
}
