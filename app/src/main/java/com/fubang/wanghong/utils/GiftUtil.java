package com.fubang.wanghong.utils;

import com.fubang.wanghong.entities.GiftEntity;
import com.fubang.wanghong.R;

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
 * <p/>
 * 项目名称：fubangzhibo
 * 类描述：
 * 创建人：dell
 * 创建时间：2016-05-25 14:22
 * 修改人：dell
 * 修改时间：2016-05-25 14:22
 * 修改备注：添加礼物的工具类
 */
public class GiftUtil {

    public static List<GiftEntity> getGifts(){
        List<GiftEntity> list = new ArrayList<>();

        list.add(new GiftEntity(32, R.mipmap.g1032,"鼓掌(10)"));
        list.add(new GiftEntity(33, R.mipmap.g1033,"啤酒(50)"));
        list.add(new GiftEntity(34, R.mipmap.g1034,"雷到了(150)"));
        list.add(new GiftEntity(35, R.mipmap.g1035,"歌神(200)"));
        list.add(new GiftEntity(36, R.mipmap.g1036,"爱心(300)"));
        list.add(new GiftEntity(37, R.mipmap.g1037,"呀美女(500)"));
        list.add(new GiftEntity(38, R.mipmap.g1038,"嗨帅哥(500)"));
        list.add(new GiftEntity(39, R.mipmap.g1039,"亲一口(1000)"));
        list.add(new GiftEntity(40, R.mipmap.g1040,"钻戒(2000)"));
        list.add(new GiftEntity(41, R.mipmap.g1041,"药不停(3000)"));
        list.add(new GiftEntity(42, R.mipmap.g1042,"小菊花(4000)"));
        list.add(new GiftEntity(43, R.mipmap.g1043,"大香蕉(5000)"));
        list.add(new GiftEntity(44, R.mipmap.g1044,"大冰棍(10W)"));
        list.add(new GiftEntity(45, R.mipmap.g1045,"梦幻城堡(100W)"));
        list.add(new GiftEntity(46, R.mipmap.g1046,"私人飞机(200W)"));
        list.add(new GiftEntity(47, R.mipmap.g1047,"豪华游轮(300W)"));
        list.add(new GiftEntity(48, R.mipmap.g1048,"浪漫烟花(400W)"));
        list.add(new GiftEntity(62, R.mipmap.g1062,"超级跑车(500W)"));
        list.add(new GiftEntity(63, R.mipmap.g1063,"热气球(1000W)"));
//        list.add(new GiftEntity(78, R.mipmap.g1078,"四大美女"));
//        list.add(new GiftEntity(79, R.mipmap.g1079,"同心结"));
//        list.add(new GiftEntity(80, R.mipmap.g1080,"红鲤鱼"));
//        list.add(new GiftEntity(81, R.mipmap.g1081,"红玫瑰"));
//        list.add(new GiftEntity(82, R.mipmap.g1082,"万事如意"));
//        list.add(new GiftEntity(83, R.mipmap.g1083,"生日快乐"));
//        list.add(new GiftEntity(84, R.mipmap.g1084,"花篮"));
//        list.add(new GiftEntity(86, R.mipmap.g1086,"爱神之箭"));
//        list.add(new GiftEntity(170, R.mipmap.g1170,"大家好"));
//        list.add(new GiftEntity(171, R.mipmap.g1171,"美女"));
//        list.add(new GiftEntity(172, R.mipmap.g1172,"飞吻"));
//        list.add(new GiftEntity(173, R.mipmap.g1173,"勾引你"));
//        list.add(new GiftEntity(174, R.mipmap.g1174,"中国好声音"));
//        list.add(new GiftEntity(175, R.mipmap.g1175,"好听"));
//        list.add(new GiftEntity(192, R.mipmap.g1192,"佛光普照"));
//        list.add(new GiftEntity(212, R.mipmap.g1212,"一生一世"));
//        list.add(new GiftEntity(528, R.mipmap.g1528,"真好听"));
//        list.add(new GiftEntity(533, R.mipmap.g1533,"人民币"));
//        list.add(new GiftEntity(536, R.mipmap.g1536,"上花轿"));
//        list.add(new GiftEntity(537, R.mipmap.g1537,"小牙签"));
//        list.add(new GiftEntity(552, R.mipmap.g1552,"情人节"));
//        list.add(new GiftEntity(562, R.mipmap.g1562,"因为有你"));
//        list.add(new GiftEntity(564, R.mipmap.g1565,"记得我爱你"));
//        list.add(new GiftEntity(567, R.mipmap.g1567,"拜拜"));
//        list.add(new GiftEntity(568, R.mipmap.g1568,"我要68"));

        return list;
    }
}
