package com.fubang.wanghong;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.RadioGroup;

import com.fubang.wanghong.R;
import com.fubang.wanghong.ui.BaseActivity;
import com.fubang.wanghong.fragment.FollowFragment_;
import com.fubang.wanghong.fragment.HomeFragment_;
import com.fubang.wanghong.fragment.LiveFragment_;
import com.fubang.wanghong.fragment.MyFragment_;
import com.loveplusplus.update.UpdateChecker;
import com.zhuyunjian.library.FragmentTabUtils;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * 主页面
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById(R.id.main_bottom_radiogp)
    RadioGroup radioGroup;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private PackageManager manager;

    private PackageInfo info = null;


    @Override
    public void initView() {
        fragments.add(HomeFragment_.builder().build());
        fragments.add(LiveFragment_.builder().build());
        fragments.add(FollowFragment_.builder().build());
        fragments.add(MyFragment_.builder().build());
        //底部按钮切换fragment的工具类
        new FragmentTabUtils(this,getSupportFragmentManager(),radioGroup,fragments, R.id.main_contaner);
        int version = Integer.parseInt(StartUtil.getVersion(this));
        manager = this.getPackageManager();

        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int  versionCode = info.versionCode;
        String versionname = intToIp(version);
        String versionName = info.versionName;
//        Log.d("123","versionname"+versionName);
//        int intversion = ipToInt(versionName);
        if (!versionname.equals(versionName)){
            UpdateChecker.checkForDialog(MainActivity.this, AppConstant.DOWNLOAD_URL);
        }
    }
    /**
     * 本机ip
     */
    @Override
    public void initData() {
        //获取wifi服务
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //判断wifi是否开启
        if (!wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
//        Log.d("123",ip);
    }
    private String intToIp(int i) {
        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }

    public int ipToInt(String ip) {
        String[] ips = ip.split("\\.");
        return (Integer.parseInt(ips[0]) << 24) + (Integer.parseInt(ips[1]) << 16)
                + (Integer.parseInt(ips[2]) << 8) + Integer.parseInt(ips[3]);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
