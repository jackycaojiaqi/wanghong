package com.fubang.wanghong.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fubang.wanghong.R;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 备用游戏页面
 * http://120.26.108.184:98/game.htm?ip=120.26.108.184&port=9999&
 * memberid=1003&passwd=4ae72303977a82903a46e7addacb7c33&
 * sysserial=01f77905-6ea6-4d6b-8b5e-edcc59487f89&mac=74-27-EA-E5-A3-DA
 */

@EActivity(R.layout.activity_game)
public class GameActivity extends BaseActivity {
    @ViewById(R.id.game_image)
    SimpleDraweeView imageView;
    @ViewById(R.id.game_jiazai)
    Button button;

    @Override
    public void initView() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                imageView.setImageURI(Uri.parse("http://120.26.127.210:9419/user_pic/20170424043324_989.jpg"));
//                imageView.setImageURI(Uri.parse("https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/holiday/habo/res/doodle/17.png"));
                imageView.setImageURI(Uri.parse("http://120.26.127.210:9419/user_pic/20170424043821_397.jpg"));
            }
        });
    }
}
