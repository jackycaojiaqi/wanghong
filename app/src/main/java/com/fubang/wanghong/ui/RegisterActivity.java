package com.fubang.wanghong.ui;//package com.fubang.newfubangzhibo.ui;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fubang.wanghong.MainActivity_;
import com.fubang.wanghong.R;
import com.xlg.android.protocol.LogonResponse;
import com.xlg.android.protocol.RegisterResponse;
import com.xlg.android.utils.Tools;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import sample.login.LoginMain;

/**
 * 注册页面
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {
    @ViewById(R.id.register_back_btn)
    ImageView backImage;
    @ViewById(R.id.register_get_yanzhen)
    Button getBtn;
    @ViewById(R.id.register_password)
    EditText pwdEdit;
    @ViewById(R.id.register_user_phone)
    EditText phoneEdit;
    @ViewById(R.id.register_user_yanzhen)
    EditText yanzhenEdit;
    @ViewById(R.id.register_register)
    Button reigsterBtn;
    private String phone;
    private String pwd;
//    EventHandler eh=new EventHandler(){
//
//        @Override
//        public void afterEvent(int event, int result, Object data) {
//
//            if (result == SMSSDK.RESULT_COMPLETE) {
//                //回调完成
//                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                    //提交验证码成功
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            new LoginMain(0, pwd, phone, 9, 9, RegisterActivity.this)
//                                    .start(0, pwd, 9, phone, 9, RegisterActivity.this);
//                        }
//                    }).start();
//                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
//                    //获取验证码成功
////                    Toast.makeText(RegisterActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
//                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
//                    //返回支持发送验证码的国家列表
//                }
//            }else{
//                ((Throwable)data).printStackTrace();
//            }
//        }
//    };
    private String code;

    @Override
    public void before() {
        super.before();

        EventBus.getDefault().register(this);
//        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @Override
    public void initView() {
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone =  phoneEdit.getText().toString();
                if (phone.length()==11) {
//                    SMSSDK.getVerificationCode("86", phone);
                }else {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        reigsterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone =  phoneEdit.getText().toString();
                pwd =  pwdEdit.getText().toString();
                code =  yanzhenEdit.getText().toString();
                if (phone.length()!=11){
                    Toast.makeText(RegisterActivity.this, "请输入11位手机号", Toast.LENGTH_SHORT).show();
                }else if (pwd.length()<6 || pwd.length()>15){
                    Toast.makeText(RegisterActivity.this, "请输入6-15位密码", Toast.LENGTH_SHORT).show();
                }else {
//                    SMSSDK.submitVerificationCode("86",phone, code);

                }

            }
        });
    }

    @Subscriber(tag = "registerSuccess")
    public void getRegisterMsg(RegisterResponse res){
        int errid = res.getErrid();
        if (errid==1){
            Toast.makeText(this, "该账号已存在", Toast.LENGTH_SHORT).show();
        }else if(errid == 0){
            final int userId = res.getUserid();
            String psd = res.getCuserpwd();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new LoginMain(userId, pwd,"", 0, 0,RegisterActivity.this).start(userId, pwd, 0, "",0,RegisterActivity.this);
                }
            }).start();

        }
    }
    @Subscriber(tag = "relogin_success")
    public void loginSuccess(LogonResponse res){
//        flag = 0;
        Tools.PrintObject(res);
        StartUtil.putCount(RegisterActivity.this,0);
        if (res != null){
//            Log.d("123",res.getHeadpic()+"length");
            if (res.getHeadpic()>15){
                StartUtil.editInfo(this, res.getCalias(), res.getUserid() + "", res.getHeadpic()+"", res.getCuserpwd());
            }else {
//                Log.d("123","不是吧这走?");
                StartUtil.editInfo(this, res.getCalias(), res.getUserid() + "", "head" + res.getHeadpic(), res.getCuserpwd());
            }
            StartUtil.putVersion(this,res.getNverison()+"");
            StartUtil.editUserInfo(this,res.getNlevel()+"",res.getNdeposit()+"",res.getNk()+"",res.getNb()+"",res.getCidiograph());
            startActivity(MainActivity_.intent(RegisterActivity.this).extra("flag",0).get());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
//        SMSSDK.unregisterEventHandler(eh);
    }

}
