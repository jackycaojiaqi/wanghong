package com.fubang.wanghong;

import java.io.File;

/**
 * 常量工具类
 * Created by dell on 2016/4/5.
 */
public class AppConstant {
    public static final String HOME_TYPE = "home_type";
//    public static final String[] HOME_TYPE_TITLE = {"大厅列表","热门主播","新人主播"};
    public static final String[] HOME_TYPE_TITLE = {"大厅列表"};
    public static final String[] ROOM_TYPE_TITLE = {"公聊","私聊","观众"};
    public static final String[] MY_TYPE_TITLE = {"VIP","座驾","活动","我的道具"};
    public static final String[] BILLBOARD_TITLE = {"动感之星","财富","礼金","房间人气"};
    public static final String BASE_URL = "http://115.231.24.84:96";
//    http://115.231.24.84:96/index.php/app/roomlist?count=40&page=1&group=1
    public static final String RICH_BASE= "http://www.sssktv.com";
//    public static final String BASE_URL = "http://"+ StartUtil.getIpPort(App.getInstance());
//    public static final String BASE_URL = "http://115.29.11.107:9493";
//    public static final  String BASE_URL = "http://m.zzzktv.com/";
    public static final String STAR_URL= "http://shuang.68hsy.com/top/";
    public static final String HEAD_URL = "http://www.sssktv.com/youliao_admin/roompic/";
//    public static final String HEAD_URL = "http://"+StartUtil.getIpPort(App.getInstance())+"/home_dhhs/upload/user/";
    public static final String DOWNLOAD_URL = "http://61.153.104.118:9418/download/wh.apk";
    public static final String VER = "ver";
    public static final String OS = "os";
    public static final String TIME = "time";
    public static final String COUNT = "count";
    public static final String PAGE = "page";
    public static final String GROUP = "group";

    //设置图片缓存地址
    public static final String IMAGE_CACHE = App_.getInstance().getCacheDir().getAbsolutePath()+"";
    public static File getCacheFile(){
        File file = new File(IMAGE_CACHE);
        return file;
    }
}
