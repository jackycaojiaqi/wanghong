package com.fubang.wanghong.utils;

import android.content.Context;

import com.zhuyunjian.library.StartUtil;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 第三方分享工具类
 * Created by dell on 2016/4/11.
 */
public class  ShareUtil {

    private static volatile ShareUtil instance = null;

    private ShareUtil(){
    }
    public static ShareUtil getInstance() {
        if (instance == null) {
            synchronized (ShareUtil.class) {
                if (instance == null) {
                    instance = new ShareUtil();
                }
            }
        }
        return instance;
    }

    public void share(Context context){
        ShareSDK.initSDK(context);
        OnekeyShare onekeyShare = new OnekeyShare();
        //关闭sso授权
        onekeyShare.disableSSOWhenAuthorize();
        onekeyShare.setTitle("网红直播");
        onekeyShare.setText("http://www.sssktv.com/");
        // imagePath是图片的本地路径：除Linked-In以外的平台都支持此参数
        //oks.setImagePath(Environment.getExternalStorageDirectory() + "/meinv.jpg");//确保SDcard下面存在此张图片

//        onekeyShare.setAddress(StartUtil.getUserId(context));

        //网络图片的url：所有平台
        onekeyShare.setImageUrl("http://www.sssktv.com/static/site/cn/kaix8/images/logo.png");//网络图片rul

        // url：仅在微信（包括好友和朋友圈）中使用
        onekeyShare.setUrl("http://www.sssktv.com/");   //网友点进链接后，可以看到分享的详情

        // Url：仅在QQ空间使用
        onekeyShare.setTitleUrl("http://www.sssktv.com/");  //网友点进链接后，可以看到分享的详情

        // 启动分享GUI
        onekeyShare.show(context);
    }
}
