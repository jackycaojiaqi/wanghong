package com.fubang.wanghong.fragment;


import android.net.Uri;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fubang.wanghong.AppConstant;
import com.fubang.wanghong.adapters.HomeTitleAdapter;
import com.fubang.wanghong.api.ApiService;
import com.fubang.wanghong.entities.HistoryEnity;
import com.fubang.wanghong.entities.HistoryListEntiy;
import com.fubang.wanghong.entities.UserEntity;
import com.fubang.wanghong.R;
import com.fubang.wanghong.ui.DaiRechargeAcitivity_;
import com.fubang.wanghong.ui.MessageActivity_;
import com.fubang.wanghong.ui.PersonActivity_;
import com.fubang.wanghong.ui.PrivilegeActivity_;
import com.fubang.wanghong.ui.RechargeActivity_;
import com.fubang.wanghong.ui.ServiceActivity_;
import com.fubang.wanghong.ui.SettingActivity_;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_my)
public class MyFragment extends BaseFragment implements Callback<HistoryListEntiy> {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
//    @ViewById(R.id.my_viewpage)
//    ViewPager viewPager;
//    @ViewById(R.id.my_tablayout)
//    TabLayout tabLayout;
    @ViewById(R.id.my_user_headicon)
    SimpleDraweeView userIcon;
    @ViewById(R.id.my_username)
    TextView userName;
    @ViewById(R.id.my_recharge)
    LinearLayout myRecharge;
    @ViewById(R.id.my_message)
    LinearLayout myMessage;
    @ViewById(R.id.my_privileges)
    LinearLayout myPrivileges;
    @ViewById(R.id.my_setting)
    LinearLayout mySetting;
    @ViewById(R.id.my_service)
    LinearLayout myService;
    @ViewById(R.id.my_follolw_number)
    TextView followNumber;
    @ViewById(R.id.my_frag_history_number)
    TextView historyNumber;
    @ViewById(R.id.my_kbi_tv)
    TextView kbiTv;
    private Call<HistoryListEntiy> call;
    private int userId;
    @Override
    public void before() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
//        final int userId = Integer.getInteger(StartUtil.getUserId(getContext()));
        for (int i = 0; i < AppConstant.MY_TYPE_TITLE.length; i++) {
            titles.add(AppConstant.MY_TYPE_TITLE[i]);
        }
        fragments.add(MyItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(0)).build());
        fragments.add(MyItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(1)).build());
        fragments.add(MyItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(2)).build());
        myRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("123",StartUtil.getUserId(getContext()));
                if (userId == 272 || userId == 528 || userId == 784 || userId == 1040 || userId == 1296 || userId == 1552){
                    startActivity(DaiRechargeAcitivity_.intent(getContext()).get());
                }else {
                    startActivity(RechargeActivity_.intent(getContext()).get());
                }
            }
        });
        myMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MessageActivity_.intent(getContext()).get());
            }
        });
        myPrivileges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PrivilegeActivity_.intent(getContext()).get());
            }
        });
        mySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SettingActivity_.intent(getContext()).get());
            }
        });
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PersonActivity_.intent(getContext()).get());
            }
        });
        myService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ServiceActivity_.intent(getContext()).get());
            }
        });
    }

    @Subscriber(tag = "followNumber")
    public void getFollowNumber(int number){
        followNumber.setText(number+"");
    }

    @Override
    public void onResume() {
        super.onResume();
        Call<HistoryListEntiy> call2 = call.clone();
        call2.enqueue(this);
    }

    @Override
    public void initData() {
        kbiTv.setText(StartUtil.getUserKbi(getContext())+" K币");
//        Log.d("123", StartUtil.getUserIcon(getContext()).length()+"length");
        if (StartUtil.getUserIcon(getContext()).length()>30){
            userIcon.setImageURI(Uri.parse(StartUtil.getUserIcon(getContext())));
        }else if (StartUtil.getUserIcon(getContext()).length()>10) {
            String url = AppConstant.HEAD_URL+ StartUtil.getUserIcon(getContext());
            userIcon.setImageURI(Uri.parse(url));
//            Log.d("123","head url-------"+url);
        }else {
                String[] pichead = StartUtil.getUserIcon(getContext()).split("\\.");
//        userIcon.setImageURI(Uri.parse(StartUtil.getUserIcon(getContext())));
//                Log.d("123", pichead[0]);
                userIcon.setImageResource(getResourceByReflect(pichead[0]));
        }
        userName.setText(StartUtil.getUserName(getContext()));
        HomeTitleAdapter adapter = new HomeTitleAdapter(getChildFragmentManager(),fragments,titles);
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppConstant.BASE_URL)
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return new Converter<ResponseBody, HistoryListEntiy>() {
                            @Override
                            public HistoryListEntiy convert(ResponseBody value) throws IOException {
                                HistoryListEntiy entity = null;
                                try {
                                    entity = new HistoryListEntiy();
                                    List<HistoryEnity> list = new ArrayList<>();
                                    JSONArray array = new JSONArray(value.string());
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject object = array.getJSONObject(i);
                                        HistoryEnity historyEnity = new HistoryEnity();
                                        historyEnity.setRoomname(object.getString("roomname"));
                                        historyEnity.setRoomid(object.getString("roomid"));
                                        historyEnity.setRoompic(object.getString("roompic"));
                                        historyEnity.setGateway(object.getString("gateway"));
                                        list.add(historyEnity);
                                    }
                                    entity.setEnities(list);
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                                return entity;
                            }
                        };
                    }
                }).build();
        ApiService service = retrofit.create(ApiService.class);
        call = service.getHistoryEnity(Integer.parseInt(StartUtil.getUserId(getContext())));
        call.enqueue(this);
    }
    /**
     * 获取图片名称获取图片的资源id的方法
     * @param imageName
     * @return
     */
    public int getResourceByReflect(String imageName){
        Class drawable  =  R.drawable.class;
        Field field = null;
        int r_id ;
        try {
            field = drawable.getField(imageName);
            r_id = field.getInt(field.getName());
        } catch (Exception e) {
            r_id= R.drawable.head0;
//            Log.e("ERROR", "PICTURE NOT　FOUND！");
        }
        return r_id;
    }

    @Subscriber(tag = "UserInfo")
    public void getUserInfo(UserEntity userEntity){
//        Log.d("123",userEntity.getUserIcon()+userEntity.getUserName());
        userIcon.setImageURI(Uri.parse(userEntity.getUserIcon()));
        userName.setText(userEntity.getUserName());
        userId = Integer.getInteger(userEntity.getUserId());
    }
    @Override
    public void onResponse(Call<HistoryListEntiy> call, Response<HistoryListEntiy> response) {
        call.cancel();
        List<HistoryEnity> list = response.body().getEnities();
        if (list!=null) {
//            Log.d("123", list.size() + "------------rich");
            historyNumber.setText(list.size()+"");
        }
    }

    @Override
    public void onFailure(Call<HistoryListEntiy> call, Throwable t) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
