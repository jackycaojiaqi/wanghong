package com.fubang.wanghong.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fubang.wanghong.App;
import com.fubang.wanghong.AppConstant;
import com.fubang.wanghong.SlidingTab.EmotionInputDetector;
import com.fubang.wanghong.SlidingTab.SlidingTabLayout;
import com.fubang.wanghong.adapters.EmotionAdapter;
import com.fubang.wanghong.adapters.FaceAdapter;
import com.fubang.wanghong.adapters.GiftAdapter;
import com.fubang.wanghong.adapters.HomeTitleAdapter;
import com.fubang.wanghong.adapters.RoomChatAdapter;
import com.fubang.wanghong.adapters.UserAdapter;
import com.fubang.wanghong.entities.FaceEntity;
import com.fubang.wanghong.entities.GiftEntity;
import com.fubang.wanghong.fragment.CommonFragment;
import com.fubang.wanghong.fragment.CommonFragment_;
import com.fubang.wanghong.fragment.HomeItemFragment_;
import com.fubang.wanghong.fragment.HotFragment_;
import com.fubang.wanghong.fragment.LookFragment;
import com.fubang.wanghong.fragment.LookFragment_;
import com.fubang.wanghong.fragment.NewFragment_;
import com.fubang.wanghong.fragment.PersonFragment;
import com.fubang.wanghong.fragment.PersonFragment_;
import com.fubang.wanghong.utils.ControllerUtil;
import com.fubang.wanghong.utils.FaceUtil;
import com.fubang.wanghong.utils.GiftUtil;
import com.fubang.wanghong.utils.GlobalOnItemClickManager;
import com.fubang.wanghong.R;
import com.xlg.android.protocol.BigGiftRecord;
import com.xlg.android.protocol.JoinRoomError;
import com.xlg.android.protocol.JoinRoomResponse;
import com.xlg.android.protocol.MicState;
import com.xlg.android.protocol.RoomChatMsg;
import com.xlg.android.protocol.RoomKickoutUserInfo;
import com.xlg.android.protocol.RoomUserInfo;
import com.xlg.android.video.AVModuleMgr;
import com.xlg.android.video.AVNotify;
import com.xlg.android.video.AudioPlay;
import com.zhuyunjian.library.StartUtil;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import sample.room.MicNotify;
import sample.room.RoomMain;

@EActivity(R.layout.activity_room_new)
public class RoomNewActivity extends BaseActivity implements MicNotify, AVNotify, View.OnClickListener, View.OnTouchListener {

    @ViewById(R.id.linear_new_container)
    LinearLayout linearLayout;
    @ViewById(R.id.edit_new_text)
    EditText editText;
    @ViewById(R.id.room_new_chat_send)
    Button sendBtn;
//    @ViewById(R.id.room_message_list)
//    ListView listView;
    @ViewById(R.id.room_new_gift)
    ImageView giftImage;
    @ViewById(R.id.chat_image_btn)
    ImageButton faceButton;
//    @ViewById(R.id.room_send_user)
//    Button userSendBtn;
    @ViewById(R.id.test_new_back_btn)
    ImageView backImage;
    @ViewById(R.id.test_new_controll)
    RelativeLayout testController;
    @ViewById(R.id.test_new_full)
    ImageView fullImage;
    @ViewById(R.id.room_new_id_test)
    TextView roomIdTv;
//    @ViewById(R.id.send_new_control)
//    LinearLayout sendControl;
    @ViewById(R.id.follow_new_image)
    ImageView followImage;
    @ViewById(R.id.text_new_back_image)
    SimpleDraweeView textBackImage;
//    @ViewById(R.id.text_new_back_image2)
//    SimpleDraweeView textBackImage2;
//    @ViewById(R.id.text_new_back_image3)
//    SimpleDraweeView textBackImage3;
    //    @ViewById(R.id.text_back_image4)
//    SimpleDraweeView textBackImage4;
//    @ViewById(R.id.text_back_image5)
//    SimpleDraweeView textBackImage5;
    @ViewById(R.id.chat_new_input_line)
    LinearLayout chatLine;
    @ViewById(R.id.text_new_relative)
    RelativeLayout textRelative;
    @ViewById(R.id.room_new_control)
    RelativeLayout roomControl;
    @ViewById(R.id.room_new_control2)
    RelativeLayout roomControl2;
    @ViewById(R.id.room_new_control3)
    RelativeLayout roomControl3;
    //    @ViewById(R.id.room_control4)
//    RelativeLayout roomControl4;
//    @ViewById(R.id.room_control5)
//    RelativeLayout roomControl5;
//    @ViewById(R.id.room_viewpager)
//    ViewPager viewPager;
//    @ViewById(R.id.room_new_change)
//    TextView textChange;

    private Button giftSendBtn;
    private GridView gridView;
    private ListView userList;
    private boolean isRunning = true;

    private List<RoomChatMsg> data = new ArrayList<>();

    private RoomChatAdapter adapter;

    private AVModuleMgr mgr;
    @ViewById(R.id.text_new_surface)
    SurfaceView surfaceView;
    @ViewById(R.id.text_new_surface2)
    SurfaceView surfaceView2;
    @ViewById(R.id.text_new_surface3)
    SurfaceView surfaceView3;
    @ViewById(R.id.car_road)
    DanmakuView danmakuView;
    private DanmakuContext mContext;
    private BaseDanmakuParser mParser;
    //    @ViewById(R.id.text_surface4)
//    SurfaceView surfaceView4;
//    @ViewById(R.id.text_surface5)
//    SurfaceView surfaceView5;
    private Bitmap bmp;
    private Bitmap bmp1;
    private Bitmap bmp2;
    private int micFlag = 0;
    private int mic0 = 0;
    private int mic1 = 1;
    private int mic2 = 2;
    private boolean mStop = false;
    private static AudioPlay play  = new AudioPlay();
    private RoomMain roomMain = new RoomMain(this);
    private EmotionInputDetector mDetector;

    private PopupWindow popupWindow;
    private PopupWindow faceWindow;
    private PopupWindow userWindow;
    private List<GiftEntity> gifts = new ArrayList<>();
    private List<FaceEntity> faces = new ArrayList<>();
    private boolean isShow = false;
    private int toid;
    private int giftId;
    private int roomId;
    private String ip;
    private int port;
    private int ssrc;
    private int topline;
    private String toName;
    private List<RoomUserInfo> userInfos = new ArrayList<>();
    private RoomUserInfo sendToUser;
    private UserAdapter userAdapter;
    private int micid;

    private String roomPwd;
    private String roomIp;
    private App app;
    private Configuration configuration;
    private int buddyid;
    private int actorid;
    private boolean followflag = true;
    private int connectNumbaer = 1;
    private List<RoomUserInfo> micUsers;
    private String mediaIp ;
    private int mediaPort;
    private int mediaRand;
    private boolean isplaying;
    private String pwd = "";

    static Activity roomActivity;
    @ViewById(R.id.game_btn)
    ImageView gameBtn;
    @ViewById(R.id.room_new_viewpager)
    ViewPager viewPager;
    @ViewById(R.id.room_new_tablayout)
    TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private int chatToFlag = 0;
    @Override
    public void before() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        app = (App) getApplication();
        roomPwd = getIntent().getStringExtra("roomPwd");
        roomIp = getIntent().getStringExtra("roomIp");
        String[] Ips = roomIp.split(";");
        String[] ports = Ips[0].split(":");
        ip = ports[0];
        port = Integer.parseInt(ports[1]);
        roomId = Integer.parseInt(getIntent().getStringExtra("roomId"));
//        RoomChatMsg joinMsg = new RoomChatMsg();
//        joinMsg.setSrcid(Integer.parseInt(StartUtil.getUserId(this)));
//        joinMsg.setSrcalias(StartUtil.getUserName(this));
//        joinMsg.setContent("加入了房间");
//        data.add(joinMsg);
//        Log.d("123",roomId+"roomId");
        EventBus.getDefault().register(this);


    }

    @ViewById(R.id.emotion_new_layout)
    RelativeLayout emotionLayout;
//    @ViewById(R.id.room_new_message_list)
//    ListView roomMessageList;
    @ViewById(R.id.edit_new_text)
    EditText roomMessageEdit;
    @ViewById(R.id.emotion_new_button)
    ImageView emotionBtn;



    @Override
    public void initView() {
        initDanmu();
        for (int i = 0; i < AppConstant.ROOM_TYPE_TITLE.length; i++) {
            titles.add(AppConstant.ROOM_TYPE_TITLE[i]);
        }
        fragments.add(CommonFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(0)).build());
        fragments.add(PersonFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(1)).build());
        fragments.add(LookFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(2)).build());
        roomIdTv.setText(roomId+"");
        roomActivity = this;
//        if (mDetector != null) {
//            mDetector = null;
//        }
        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(emotionLayout)
                .bindToContent(chatLine)
                .bindToEditText(roomMessageEdit)
                .bindToEmotionButton(emotionBtn)
                .build();

        setUpEmotionViewPager();
//        textBackImage.setVisibility(View.VISIBLE);
//        surfaceView.setVisibility(View.GONE);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        roomIdTv.setText(roomId+"");

        ControllerUtil.showAndHide(testController,roomControl);
//        Log.d("123","oncreate---");
        RoomUserInfo roomUser = new RoomUserInfo();
        roomUser.setUseralias("大厅");
        userInfos.add(roomUser);

        adapter = new RoomChatAdapter(data,this);
        configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            listView.setAdapter(adapter);
            showWindow();
//            showFace();
//            showUser();
        }

//        textChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                finish();
////                startActivity(RoomLandActivity_.intent(RoomActivity.this).extra("roomIp",roomIp).extra("roomId",roomId+"").get());
//
//                if (micUsers.size() != 1 && mgr != null) {
//                    mgr.DelAudioStream(ssrc);
//                    mgr.DelVideoStream(ssrc);
//                    if (toid == micUsers.get(0).getUserid()) {
//                        buddyid = micUsers.get(1).getUserid();
//                        toName = micUsers.get(1).getUseralias();
//                        StartAV(mediaIp, mediaPort, mediaRand, micUsers.get(micUsers.size() - 1).getUserid());
//                    }else {
//                        buddyid = micUsers.get(0).getUserid();
//                        toName = micUsers.get(0).getUseralias();
//                        StartAV(mediaIp, mediaPort, mediaRand, micUsers.get(0).getUserid());
//                    }
//                }
//            }
//        });
        //收藏房间
        followImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roomMain.getRoom().getChannel().followRoom(roomId,Integer.parseInt(StartUtil.getUserId(RoomNewActivity.this)));
                Toast.makeText(RoomNewActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        });
        //全屏切换
        fullImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(RoomLandActivity_.intent(RoomNewActivity.this).extra("roomIp",roomIp).extra("roomId",roomId+"").extra("roomPwd",roomPwd+"").get());
            }
        });
        roomControl.setOnTouchListener(this);
        //游戏
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = StartUtil.getUserPwd(RoomNewActivity.this);
                String mdPwd = stringToMD5(pwd);
                String userId = StartUtil.getUserId(RoomNewActivity.this);
                String mac = StartUtil.getDeviceId(RoomNewActivity.this);
                StringBuilder gameUrl = new StringBuilder(GAME_URL);
                //游戏地址url
                gameUrl.append(userId).append("&passwd=")
                        .append(mdPwd).append("&sysserial=01f77905-6ea6-4d6b-8b5e-edcc59487f89&mac=")
                        .append(mac);
                Uri uri=Uri.parse(gameUrl.toString());//网址一定要加http
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
//                startActivity(GameActivity_.intent(RoomNewActivity.this).get());
            }
        });
//        roomControl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        roomControl2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if (micUsers.size()>1) {
//                textBackImage.setVisibility(View.VISIBLE);
//                surfaceView.setVisibility(View.GONE);
//                textBackImage2.setVisibility(View.VISIBLE);
//                surfaceView2.setVisibility(View.GONE);
//                int a = mic0;
//                mic0 = mic1;
//                mic1 = a;
////                }
//            }
//        });
//        roomControl3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if (micUsers.size()>2) {
//                textBackImage.setVisibility(View.VISIBLE);
//                surfaceView.setVisibility(View.GONE);
//                textBackImage3.setVisibility(View.VISIBLE);
//                surfaceView3.setVisibility(View.GONE);
//                int a = mic0;
//                mic0 = mic2;
//                mic2 = a;
////                }
//            }
//        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                chatToFlag = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public static final String GAME_URL = "http://120.26.108.184:98/game.htm?ip=120.26.108.184&port=9999&memberid=";
    /**
     * 将字符串转成MD5值
     *
     * @param string
     * @return
     */
    public static String stringToMD5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString();
    }

    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }
    //表情页面
    private void setUpEmotionViewPager() {
        final String[] titles = new String[]{"经典", "vip"};
        EmotionAdapter mViewPagerAdapter = new EmotionAdapter(getSupportFragmentManager(), titles);
        final ViewPager mViewPager = (ViewPager) findViewById(R.id.new_pager);
//        if (mViewPager != null) {
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewPager.setCurrentItem(0);
//        }
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_new_tabs);
        slidingTabLayout.setCustomTabView(R.layout.widget_tab_indicator, R.id.text);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorPrimary));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mViewPager);

        GlobalOnItemClickManager globalOnItemClickListener = GlobalOnItemClickManager.getInstance();
        globalOnItemClickListener.attachToEditText((EditText)findViewById(R.id.edit_new_text));

    }
//    private void showUser() {
//        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.pop_user_list,null);
//        userList = (ListView) view.findViewById(R.id.room_user_list);
//
//        userWindow = new PopupWindow(view);
//        userWindow.setFocusable(true);
//        userAdapter = new UserAdapter(userInfos,this);
//        userList.setAdapter(userAdapter);
//
//        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Log.d("123",gifts.get(position)+"------------>");
//                sendToUser = userInfos.get(position);
//                userSendBtn.setText(sendToUser.getUseralias());
//                userWindow.dismiss();
//            }
//        });
//        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//        int height = wm.getDefaultDisplay().getHeight();
//        int width = wm.getDefaultDisplay().getWidth();
//        userWindow.setWidth(width/5);
//        ColorDrawable dw = new ColorDrawable(0xb0ffffff);
//        userWindow.setBackgroundDrawable(dw);
//        userWindow.setHeight(height/2);
//        userWindow.setOutsideTouchable(true);
//    }
//
//    private void showFace() {
//        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.pop_face_grid,null);
//        gridView = (GridView) view.findViewById(R.id.room_face_list);
//
//        faceWindow = new PopupWindow(view);
//        faceWindow.setFocusable(true);
//        faces.addAll(FaceUtil.getFaces());
//        FaceAdapter faceAdapter = new FaceAdapter(faces,this);
//        gridView.setAdapter(faceAdapter);
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Log.d("123",faces.get(position)+"------------>");
//                if (position < 9) {
//                    int faceNumber = position + 1;
//                    editText.setText(editText.getText() + "/mr70" + faceNumber);
//                }
//                if (position >= 9) {
//                    int faceNumber = position + 1;
//                    editText.setText(editText.getText() + "/mr7" + faceNumber);
//                }
//                faceWindow.dismiss();
//            }
//        });
//        faceWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        ColorDrawable dw = new ColorDrawable(0xb0ffffff);
//        faceWindow.setBackgroundDrawable(dw);
//        faceWindow.setHeight(300);
//
//        faceWindow.setOutsideTouchable(true);
//    }
    private  EditText giftToUser;
    //礼物的悬浮框
    private void showWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.pop_gift_grid,null);
        gridView = (GridView) view.findViewById(R.id.room_gift_list);
        giftSendBtn = (Button) view.findViewById(R.id.gift_send_btn);
        final TextView giftName  = (TextView)view.findViewById(R.id.gift_name_txt);
        final EditText giftCount = (EditText) view.findViewById(R.id.gift_count);
//        final EditText
                giftToUser = (EditText)view.findViewById(R.id.gift_to_user);
        popupWindow = new PopupWindow(view);
        popupWindow.setFocusable(true);
        gifts.addAll(GiftUtil.getGifts());
        GiftAdapter giftAdapter = new GiftAdapter(gifts,this);
        gridView.setAdapter(giftAdapter);
        //选择礼物
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("123",gifts.get(position)+"------------>");
                giftId = gifts.get(position).getGiftId();
                String name = gifts.get(position).getGiftName();
                giftName.setText(name+"");
//                popupWindow.dismiss();
            }
        });
        if (micUsers != null) {
            for (int i = 0; i < micUsers.size(); i++) {
                if (micUsers.get(i).getMicindex() == micFlag) {
                    giftToUser.setText(micUsers.get(i).getUseralias() + "(" + micUsers.get(i).getUserid() + ")");
//                            Log.d("123","toid---"+toid+"toName"+toName);
                }
            }
        }
        //发送礼物
        giftSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (giftToUser.getText().toString().equals("1")){
                    for (int i = 0; i < micUsers.size(); i++) {
                        if (micUsers.get(i).getMicindex()==micFlag){
                            toid = micUsers.get(i).getUserid();
                            toName = micUsers.get(i).getUseralias();
//                            Log.d("123","toid---"+toid+"toName"+toName);
                        }
                    }
//                }else if (giftToUser.getText().toString().equals("2")){
//                    for (int i = 0; i < micUsers.size(); i++) {
//                        if (micUsers.get(i).getMicindex()==1){
//                            toid = micUsers.get(i).getUserid();
//                            toName = micUsers.get(i).getUseralias();
////                            Log.d("123","toid---"+toid+"toName"+toName);
//                        }
//                    }
//                }else if (giftToUser.getText().toString().equals("3")){
//                    for (int i = 0; i < micUsers.size(); i++) {
//                        if (micUsers.get(i).getMicindex()==2){
//                            toid = micUsers.get(i).getUserid();
//                            toName = micUsers.get(i).getUseralias();
////                            Log.d("123","toid---"+toid+"toName"+toName);
//                        }
//                    }
//                }else
////                        (giftToUser.getText().toString()!="1" &&giftToUser.getText().toString()!="2"&&giftToUser.getText().toString()!="3")
//                {
//                    Toast.makeText(RoomNewActivity.this, "赠送麦序错误,请重新选择", Toast.LENGTH_SHORT).show();
//                    toid = -1;
//                    toName = "";
//                }
                int count = Integer.parseInt(giftCount.getText().toString());
                Log.d("123","toid--"+toid+"---giftId---"+giftId+"---count---"+count+"---toName---"+toName);
                roomMain.getRoom().getChannel().sendGiftRecord(toid,giftId,count,toName, StartUtil.getUserName(RoomNewActivity.this));
                toid = -1;
                toName = "";
                giftName.setText("送给");
                popupWindow.dismiss();
//                sendControl.setVisibility(View.VISIBLE);
            }
        });
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(dw);
//        popupWindow.showAsDropDown(giftImage);
//        popupWindow.showAtLocation(roomInputLinear,Gravity.BOTTOM,0,0);
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        popupWindow.setHeight(height/2);
        popupWindow.setOutsideTouchable(true);
    }
    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linear_new_container:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                break;
            case R.id.chat_image_btn:
                popupWindow.dismiss();
                if (faceWindow.isShowing()){
                    faceWindow.dismiss();
                }else {
//                    Log.d("123","showPop------------------");
//                    faceWindow.showAsDropDown(faceButton);
                    //防止虚拟软键盘被弹出菜单遮住
                    faceWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                }
                break;
            //点击礼物图标
            case R.id.room_new_gift:
//                faceWindow.dismiss();
                if (popupWindow.isShowing()){
                    popupWindow.dismiss();
//                    sendControl.setVisibility(View.VISIBLE);
                }else {
//                    Log.d("123","showPop------------------");
                    popupWindow.showAsDropDown(giftImage);
                    if (micUsers != null) {
                        for (int i = 0; i < micUsers.size(); i++) {
                            if (micUsers.get(i).getMicindex() == micFlag) {
                                giftToUser.setText(micUsers.get(i).getUseralias() + "(" + micUsers.get(i).getUserid() + ")");
//                            Log.d("123","toid---"+toid+"toName"+toName);
                            }
                        }
                    }
//                    sendControl.setVisibility(View.GONE);
                    //防止虚拟软键盘被弹出菜单遮住
//                    popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                }
                break;
//            case R.id.room_send_user:
//                if (userWindow.isShowing()){
//                    userWindow.dismiss();
//                }else {
//                    userWindow.showAsDropDown(userSendBtn);
//                }
//                break;

        }
    }
    @Override
    public void initData() {
        HomeTitleAdapter adapter = new HomeTitleAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

//        tabLayout.setVisibility(View.GONE);
        //发送聊天消息
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d("111","chatToFlag--->"+chatToFlag+"---sendtouser"+sendToUser.getUserid());
                    if (!TextUtils.isEmpty(editText.getText())) {
//                        if (userSendBtn.getText().toString().contains("大厅")) {
                        if (chatToFlag == 0) {
                            roomMain.getRoom().getChannel().sendChatMsg(0, (byte) 0, (byte) 0, "<FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:14px; COLOR:#000000\">" + editText.getText() + "</FONT>", StartUtil.getUserName(RoomNewActivity.this), Integer.parseInt(StartUtil.getUserLevel(RoomNewActivity.this)));
                            editText.setText("");
//                        } else if (!TextUtils.isEmpty(sendToUser.getUseralias())) {
                        }else if(sendToUser != null){
                            roomMain.getRoom().getChannel().sendChatMsg(sendToUser.getUserid(), (byte) 0, (byte) 1, "<FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:14px; COLOR:#000000\">" + editText.getText() + "</FONT>", StartUtil.getUserName(RoomNewActivity.this),Integer.parseInt(StartUtil.getUserLevel(RoomNewActivity.this)));
                            editText.setText("");
                        } else {
                            roomMain.getRoom().getChannel().sendChatMsg(0, (byte) 0, (byte) 0, "<FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:14px; COLOR:#000000\">" + editText.getText() + "</FONT>", StartUtil.getUserName(RoomNewActivity.this),Integer.parseInt(StartUtil.getUserLevel(RoomNewActivity.this)));
                            editText.setText("");
                        }
                    }
                }
            });
            giftImage.setOnClickListener(this);
//            faceButton.setOnClickListener(this);
            linearLayout.setOnClickListener(this);
//            userSendBtn.setOnClickListener(this);
//            mDetector = EmotionInputDetector.with(this)
//                    .setEmotionView(emotionLayout)
//                    .bindToContent(roomMessageList)
//                    .bindToEditText(roomMessageEdit)
//                    .bindToEmotionButton(emotionBtn)
//                    .build();
        }
    }
//    //接收礼物消息更新
//    @Subscriber(tag="BigGiftRecord")
//    public void getGiftRecord(BigGiftRecord obj){
//        int getGiftId = obj.getGiftid();
//        int count = obj.getCount();
//        String giftTxt = "";
//        if (count != 0) {
//            for (int i = 0; i < gifts.size(); i++) {
//                if (getGiftId == gifts.get(i).getGiftId()) {
//                    if (getGiftId < 10)
//                        giftTxt = "/g100" + getGiftId + "   x " + count;
//                    if (getGiftId >= 10 && getGiftId < 100)
//                        giftTxt = "/g10" + getGiftId + "   x " + count;
//                    if (getGiftId >= 100)
//                        giftTxt = "/g1" + getGiftId + "    x" + count;
//                    if (getGiftId>549 && getGiftId<563) {
//                        RoomChatMsg msg = new RoomChatMsg();
//                        msg.setToid(-1);
//                        msg.setContent("g" + getGiftId + "");
//                        msg.setSrcid(obj.getSrcid());
//                        msg.setSrcalias(obj.getSrcalias());
//                        msg.setDstvcbid(count);
//                        data.add(msg);
//                        adapter.notifyDataSetChanged();
////                        listView.setSelection(listView.getCount() - 1);
//                    }
//                }
//            }
//        }
//    }

    //私聊发送
    @Subscriber(tag = "SendToUser")
    public void getSendToUser(RoomUserInfo obj){
        sendToUser = obj;
        viewPager.setCurrentItem(1,true);
    }
    //踢出房间
    @Subscriber(tag = "KickOut")
    public void getKickOut(RoomUserInfo obj){
        roomMain.getRoom().getChannel().kickOutRoom(obj.getUserid());
    }
    //禁言
    @Subscriber(tag = "ForbidChat")
    public void getForbidChat(RoomUserInfo obj){
        roomMain.getRoom().getChannel().forbidChat(obj.getUserid(),(short)1);
    }
    //取消禁言
    @Subscriber(tag = "CancelForbidChat")
    public void getCancelForbidChat(RoomUserInfo obj){
        roomMain.getRoom().getChannel().forbidChat(obj.getUserid(),(short)0);
    }
    //加入房间错误
    @Subscriber(tag = "joinRoomError")
    public void jionRoomError(final int err){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
        if (err == 503){
            Toast.makeText(this, "房间密码错误请输入", Toast.LENGTH_SHORT).show();
            // 弹出自定义dialog
            LayoutInflater inflater = LayoutInflater.from(RoomNewActivity.this);
            View view = inflater.inflate(R.layout.dialog_input_pwd, null);

            // 对话框
            final Dialog dialog = new Dialog(RoomNewActivity.this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.show();

            // 设置宽度为屏幕的宽度
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = (int) (display.getWidth()); // 设置宽度
            dialog.getWindow().setAttributes(lp);
            dialog.getWindow().setContentView(view);
            dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

            final EditText et_obj = (EditText) view.findViewById(R.id.et_obj);// 密码
            Button tv_go = (Button) view.findViewById(R.id.tv_go);// 确认
            Button iv_close = (Button) view.findViewById(R.id.tv_return);// 取消
            tv_go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwd = et_obj.getText().toString();
                    dialog.dismiss();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            play.start();
                            roomMain.Start(roomId,Integer.parseInt(StartUtil.getUserId(RoomNewActivity.this)), StartUtil.getUserPwd(RoomNewActivity.this), ip, port, pwd);
                        }
                    }).start();
                }
            });
            iv_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else if(err == 406 || err == 407 || err == 408){
            Toast.makeText(RoomNewActivity.this, "您已被封号请联系客服", Toast.LENGTH_SHORT).show();
            finish();
        }else if(err == 417){
            Toast.makeText(RoomNewActivity.this, "该房间限制等级进入", Toast.LENGTH_SHORT).show();
        }
//            }
//        });

    }
    //加入房间失败时尝试换ip端口号再加入
    @Subscriber(tag = "ConnectFailed")
    public void Reconnect(boolean failed) {
        if (connectNumbaer < 4) {
            String[] Ips = roomIp.split(";");
            String[] ports = Ips[connectNumbaer].split(":");
            ip = ports[0];
            port = Integer.parseInt(ports[1]);
            roomId = Integer.parseInt(getIntent().getStringExtra("roomId"));
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isRunning) {
                        play.start();
//                        Log.d("123", "chongxingqidong");
                        roomMain.Start(roomId, Integer.parseInt(StartUtil.getUserId(RoomNewActivity.this)), StartUtil.getUserPwd(RoomNewActivity.this), ip, port,pwd);
                    }
                }
            }).start();
            connectNumbaer ++;
        }else {
            Toast.makeText(RoomNewActivity.this, "加入房间失败", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    //加入房间返回消息
    @Subscriber(tag = "JoinRoomResponse")
    public void getJoinRoomResponse(JoinRoomResponse obj){
//        topline = obj.getTopline() - 1000;
    }
    //接收服务器发送的消息更新列表
    @Subscriber(tag="RoomChatMsg")
    public void getRoomChatMsg(RoomChatMsg msg){
//        Log.d("123",msg.getContent());
        if(msg.getMsgtype() == 0) {
            if (msg.getIsprivate() == 0){
                //("<b><FONT style=\"FONT-FAMILY:宋体;FONT-SIZE:17px; COLOR:#FF0000\">/mr599</FONT></b>")) {
                //<b><FONT style="FONT-FAMILY:宋体;FONT-SIZE:17px; COLOR:#FF0000">/mr599</FONT></b>
                EventBus.getDefault().post(msg,"CommonMsg");
//                listView.setSelection(listView.getCount() - 1);
            }
        }
        if(msg.getMsgtype() == 0) {
            if (msg.getIsprivate() == 1) {
                if (msg.getToid() == sendToUser.getUserid() || msg.getToid() == Integer.parseInt(StartUtil.getUserId(this))) {
                    EventBus.getDefault().post(msg,"PersonMsg");
                }
            }
        }
        if (msg.getMsgtype() == 12 && msg.getSrcid() == 2) {

            Spanned spanned = Html.fromHtml(msg.getContent());
            Log.d("123",spanned+"");
            addDanmaku(false, spanned);
        }
    }
//    =>Class: class com.xlg.android.protocol.RoomChatMsg
//    t: <FONT style="color=purple; font-size:17px">祝贺<FONT color=#2248DD>1616(1616)</FONT>在【<FONT color=#ff6900>争霸车行</FONT>】赢得金币:<FONT color=#fc000a>11270000</FONT></FONT>
//    =>Dstplatformid: 0
//    =>Dstvcbid: 0
//    =>Familyid: 0
//    =>Isprivate: 0
//    =>Msgtype: 12
//    =>Srcalias:
//    =>Srcid: 2
//    =>Srclevel: 0
//    =>Srcplatformid: 0
//    =>Srcsealid: 0
//    =>Textlen: 177
//    =>Toalias:
//    =>Toid: 10000
//    =>Vcbid: 0
    //添加弹幕
    private void addDanmaku(boolean islive, Spanned chatmsg) {
        BaseDanmaku danmaku = mContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || danmakuView == null) {
            return;
        }
        danmaku.text = chatmsg;
        danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
        danmaku.isLive = islive;
        danmaku.time = danmakuView.getCurrentTime() + 4800;
        danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
        danmaku.textColor = Color.WHITE;
        danmaku.textShadowColor = Color.WHITE;
        danmakuView.addDanmaku(danmaku);

    }
    //弹幕初始化
    public void initDanmu(){
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 1); // 滚动弹幕最大显示5行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);


        mContext = DanmakuContext.create();
        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(1.2f).setScaleTextSize(1.2f)
                .setCacheStuffer(new SpannedCacheStuffer(), new BaseCacheStuffer.Proxy() {
                    @Override
                    public void prepareDrawing(BaseDanmaku danmaku, boolean fromWorkerThread) {
                        danmakuView.invalidateDanmaku(danmaku, false);
                    }

                    @Override
                    public void releaseResource(BaseDanmaku danmaku) {

                    }
                }) // 图文混排使用SpannedCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);
        if (danmakuView != null) {
            mParser = createParser(this.getResources().openRawResource(R.raw.comments));
            danmakuView.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
                @Override
                public void updateTimer(DanmakuTimer timer) {
                }
                @Override
                public void drawingFinished() {
                }
                @Override
                public void danmakuShown(BaseDanmaku danmaku) {
                }
                @Override
                public void prepared() {
                    danmakuView.start();
                }
            });

            danmakuView.prepare(mParser, mContext);
            danmakuView.enableDanmakuDrawingCache(true);

        }

    }
    private BaseDanmakuParser createParser(InputStream stream) {

        if (stream == null) {
            return new BaseDanmakuParser() {

                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }

        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        BaseDanmakuParser parser = new BiliDanmukuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;

    }
//    //用户离开房间
//    @Subscriber(tag = "RoomKickoutUserInfo")
//    public void getUserOut(RoomKickoutUserInfo obj){
//        int leaveId = obj.getToid();
//        for (int i = 0; i < userInfos.size(); i++) {
//            if (userInfos.get(i).getUserid() == leaveId){
//                userInfos.remove(i);
//            }
//        }
//        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            userAdapter.notifyDataSetChanged();
//            userList.setAdapter(new UserAdapter(userInfos, this));
//        }
//    }
//    //获取用户列表
//    @Subscriber(tag = "userList")
//    public void getUserList(RoomUserInfo userInfo){
//        userInfos.add(userInfo);
//        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            userAdapter.notifyDataSetChanged();
//            userList.setAdapter(new UserAdapter(userInfos, this));
//        }
////        Log.d("123",userInfo.getUserid()+"-----------<<");
//    }

    //开始加入房间
    @Override
    protected void onResume() {
        super.onResume();
        micUsers = new ArrayList<>();
        configuration = getResources().getConfiguration();
        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    play.start();
//                    Log.d("123", "chongxingqidong");
                    roomMain.Start(roomId, Integer.parseInt(StartUtil.getUserId(RoomNewActivity.this)), StartUtil.getUserPwd(RoomNewActivity.this), ip, port, pwd);
                }
            }
        }).start();
    }

    //停止视频流,结束离开房间
    @Override
    public void onStop() {
//        Log.d("123","onStop---");
        mStop = true;
        isRunning = false;
        super.onStop();
        if (mgr != null){
            final AVModuleMgr tmp = mgr;
            mgr = null;
            play.stop();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    tmp.StopRTPSession();
                    tmp.Uninit();
                }
            }).start();

        }
        roomMain.getRoom().getChannel().sendLeaveRoom(Integer.parseInt(StartUtil.getUserId(this)));
        roomMain.getRoom().onDisconnected();
    }
    //上麦提示
    @Subscriber(tag = "upMicState")
    public void upMicState(MicState obj){
        for (int i = 0; i < userInfos.size(); i++) {
            if (obj.getUserid()==userInfos.get(i).getUserid()){
                userInfos.get(i).setMicindex(obj.getMicindex());
                micUsers.add(userInfos.get(i));
            }
        }
        micid = obj.getUserid();
//        ssrc = ~micid + 0x1314;
        ssrc = micid;
        mgr.AddRTPRecver(0, ssrc, 99, 1000);
        mgr.SetRTPRecverARQMode(ssrc, 99, 1);

        mgr.AddRTPRecver(0, ssrc, 97, 1000);
        mgr.SetRTPRecverARQMode(ssrc, 97, 1);
//        Log.d("123","ssrc====="+ssrc);
        mgr.AddVideoStream(ssrc,  0, 1, this);
        if (!isplaying)
            mgr.AddAudioStream(ssrc,1,this);
        mgr.CreateRTMPRecver("rtmp://pili-live-rtmp.fbyxsp.com/wanghong/wh_"+roomId+"_"+ssrc, ssrc);
    }
    //下麦提示
    @Subscriber(tag = "downMicState")
    public void downMicState(MicState obj){
        for (int i = 0; i < micUsers.size(); i++) {
            if (micUsers.get(i).getUserid()==obj.getUserid()){
                micUsers.remove(i);
            }
        }
//        textBackImage.setVisibility(View.VISIBLE);
//        surfaceView.setVisibility(View.GONE);
//        textBackImage2.setVisibility(View.VISIBLE);
//        surfaceView2.setVisibility(View.GONE);
//        textBackImage3.setVisibility(View.VISIBLE);
//        surfaceView3.setVisibility(View.GONE);
    }
    //麦上几个人就添加视频流
    @Subscriber(tag = "onMicUser")
    public void getonMicUser(RoomUserInfo obj){
        textBackImage.setVisibility(View.GONE);
        if (obj.getMicindex() == 0){
            sendToUser = obj;
        }
        micUsers.add(obj);
//        actorid = obj.getActorid();
//        buddyid = obj.getUserid();
//        toName = obj.getUseralias();
        micid = obj.getUserid();
//        Log.d("123","micid====="+micid);
//        ssrc = ~micid + 0x1314;
        ssrc = micid;
        mgr.AddRTPRecver(0, ssrc, 99, 1000);
        mgr.SetRTPRecverARQMode(ssrc, 99, 1);

        mgr.AddRTPRecver(0, ssrc, 97, 1000);
        mgr.SetRTPRecverARQMode(ssrc, 97, 1);
//        Log.d("123","ssrc====="+ssrc);
        mgr.AddVideoStream(ssrc,  0, 1, this);
        if (!isplaying)
            mgr.AddAudioStream(ssrc,1,this);
        mgr.CreateRTMPRecver("rtmp://pili-live-rtmp.fbyxsp.com/wanghong/wh_"+roomId+"_"+ssrc, ssrc);
    }
    //开始接收添加视频接收流
    public void StartAV(String ip, int port, int rand, int uid) {
//        if (mgr != null) {
//        mgr.Init();
//        Log.d("123", "===uid" + uid);
//        mgr.CreateRTPSession(0);
//        mgr.SetServerAddr2(ip, port, 0);
//        mgr.StartRTPSession();
//        }
//        toid = uid;

//        if (rand < 1800000000)
//            rand = 1800000000;
//        ssrc = ~uid + 0x1314;
        ssrc = uid;
        mgr.AddRTPRecver(0, ssrc, 99, 1000);
        mgr.SetRTPRecverARQMode(ssrc, 99, 1);

        mgr.AddRTPRecver(0, ssrc, 97, 1000);
        mgr.SetRTPRecverARQMode(ssrc, 97, 1);
        Log.d("123","ssrc====="+ssrc);
        isplaying = true;
        mgr.AddAudioStream(ssrc, 1, this);
        mgr.AddVideoStream(ssrc,  0, 1, this);


        mgr.InitCDNSDK();
        mgr.CreateRTMPRecver("rtmp://pili-live-rtmp.fbyxsp.com/wanghong/wh_"+roomId+"_"+ssrc, ssrc);




//        if (micUsers!=null){
//            Log.d("123","micUsers==null?"+micUsers.size());
//            for (int i = 0; i < micUsers.size(); i++) {
//                micid = micUsers.get(i).getUserid();
//                Log.d("123","micid====="+micid);
//                ssrc = ~micid + 0x1314;
//
//                mgr.AddRTPRecver(0, ssrc, 99, 1000);
//                mgr.SetRTPRecverARQMode(ssrc, 99, 1);
//
//                mgr.AddRTPRecver(0, ssrc, 97, 1000);
//                mgr.SetRTPRecverARQMode(ssrc, 97, 1);
//                Log.d("123","ssrc====="+ssrc);
//                mgr.AddVideoStream(ssrc,  0, 1, this);
//            }
//        }
    }
    //接收视频流
    @Override
    public void onVideo(int ssrc, int width, int height, byte[] img) {
        if(false != mStop) {
            return;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(true);
//        paint.setDither(true);
//        Paint paint = new Paint(Paint.DITHER_FLAG);
//        System.out.println("==ssrc"+ssrc+"======== onVideo: " + width + ":" + height + "(" + img.length + ")");
        //判断几个在麦序上
        if (micUsers.size() == 1){
//        actorid = obj.getActorid();
//            buddyid = micUsers.get(0).getUserid();
//            toName = micUsers.get(0).getUseralias();
//            micid = micUsers.get(0).getUserid();
            //一麦显示一麦,二麦显示二麦,三麦显示三麦
            if ((micUsers.get(0).getMicindex() == mic0 && ssrc == (~micUsers.get(0).getUserid() + 0x1314))) {
//                            textBackImage.setVisibility(View.GONE);
//                            surfaceView.setVisibility(View.VISIBLE);
                // 删除旧的
                if (null != bmp) {
                    if (width != bmp.getWidth() || height != bmp.getHeight()) {
                        bmp = null;
                    }
                }

                // 创建新的
                if (null == bmp) {
                    bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                }

                ByteBuffer buf = ByteBuffer.wrap(img);
                bmp.copyPixelsFromBuffer(buf);
                // 这是在线程里操作的，千万不要直接在画布上绘制
                // 在surfaceView中显示
                if (null != surfaceView.getHolder()) {
                    Canvas canvas = surfaceView.getHolder().lockCanvas();


                    if (null != canvas) {
                        try {
                            Rect rt1 = new Rect(0, 0, surfaceView.getWidth(), surfaceView.getHeight());
                            Rect rt2 = new Rect(0, 0, width, height);
                            canvas.drawBitmap(bmp, rt2, rt1, null);
                        } finally {
                            // 必须要释放，不然下次不会再绘制
                            surfaceView.getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }else if ((micUsers.get(0).getMicindex() == mic1 && ssrc == (~micUsers.get(0).getUserid() + 0x1314))){
//                if (textBackImage2.isShown()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textBackImage2.setVisibility(View.GONE);
//                            surfaceView2.setVisibility(View.VISIBLE);
//                        }
//                    });
//                }
                // 删除旧的
                if (null != bmp1) {
                    if (width != bmp1.getWidth() || height != bmp1.getHeight()) {
                        bmp1 = null;
                    }
                }

                // 创建新的
                if (null == bmp1) {
                    bmp1 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                }

                ByteBuffer buf = ByteBuffer.wrap(img);
                bmp1.copyPixelsFromBuffer(buf);
                // 这是在线程里操作的，千万不要直接在画布上绘制
                // 在surfaceView中显示
                if (null != surfaceView2.getHolder()) {
                    Canvas canvas = surfaceView2.getHolder().lockCanvas();

                    if (null != canvas) {
                        try {
                            Rect rt1 = new Rect(0, 0, surfaceView2.getWidth(), surfaceView2.getHeight());
                            Rect rt2 = new Rect(0, 0, width, height);
                            canvas.drawBitmap(bmp1, rt2, rt1, null);
                        } finally {
                            // 必须要释放，不然下次不会再绘制
                            surfaceView2.getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }else if ((micUsers.get(0).getMicindex() == mic2 && ssrc == (~micUsers.get(0).getUserid() + 0x1314)))
            {
//                if (textBackImage3.isShown()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textBackImage3.setVisibility(View.GONE);
//                            surfaceView3.setVisibility(View.VISIBLE);
//                        }
//                    });
//                }
                // 删除旧的
                if (null != bmp2) {
                    if (width != bmp2.getWidth() || height != bmp2.getHeight()) {
                        bmp2 = null;
                    }
                }

                // 创建新的
                if (null == bmp2) {
                    bmp2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                }

                ByteBuffer buf = ByteBuffer.wrap(img);
                bmp2.copyPixelsFromBuffer(buf);
                // 这是在线程里操作的，千万不要直接在画布上绘制
                // 在surfaceView中显示
                if (null != surfaceView3.getHolder()) {
                    Canvas canvas = surfaceView3.getHolder().lockCanvas();

                    if (null != canvas) {
                        try {
                            Rect rt1 = new Rect(0, 0, surfaceView3.getWidth(), surfaceView3.getHeight());
                            Rect rt2 = new Rect(0, 0, width, height);
                            canvas.drawBitmap(bmp2, rt2, rt1, null);
                        } finally {
                            // 必须要释放，不然下次不会再绘制
                            surfaceView3.getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }
        if (micUsers.size()==2){
//            if (micUsers.get(0).getMicindex()==0){
//                buddyid = micUsers.get(0).getUserid();
//                toName = micUsers.get(0).getUseralias();
//                micid = micUsers.get(0).getUserid();
//            }else if (micUsers.get(1).getMicindex()==0){
//                buddyid = micUsers.get(1).getUserid();
//                toName = micUsers.get(1).getUseralias();
//                micid = micUsers.get(1).getUserid();
//            }
            if ((micUsers.get(0).getMicindex() == mic0 && ssrc == (~micUsers.get(0).getUserid() + 0x1314)) ||
                    (micUsers.get(1).getMicindex() == mic0 && ssrc == (~micUsers.get(1).getUserid() + 0x1314))) {
//                            textBackImage.setVisibility(View.GONE);
//                            surfaceView.setVisibility(View.VISIBLE);
                // 删除旧的
                if (null != bmp) {
                    if (width != bmp.getWidth() || height != bmp.getHeight()) {
                        bmp = null;
                    }
                }

                // 创建新的
                if (null == bmp) {
                    bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                }

                ByteBuffer buf = ByteBuffer.wrap(img);
                bmp.copyPixelsFromBuffer(buf);
                // 这是在线程里操作的，千万不要直接在画布上绘制
                // 在surfaceView中显示
                if (null != surfaceView.getHolder()) {
                    Canvas canvas = surfaceView.getHolder().lockCanvas();

                    if (null != canvas) {
                        try {
                            Rect rt1 = new Rect(0, 0, surfaceView.getWidth(), surfaceView.getHeight());
                            Rect rt2 = new Rect(0, 0, width, height);
                            canvas.drawBitmap(bmp, rt2, rt1, null);
                        } finally {
                            // 必须要释放，不然下次不会再绘制
                            surfaceView.getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }else if ((micUsers.get(0).getMicindex() == mic1 && ssrc == (~micUsers.get(0).getUserid() + 0x1314)) ||
                    (micUsers.get(1).getMicindex() == mic1 && ssrc == (~micUsers.get(1).getUserid() + 0x1314))){
//                if (textBackImage2.isShown()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textBackImage2.setVisibility(View.GONE);
//                            surfaceView2.setVisibility(View.VISIBLE);
//                        }
//                    });
//                }
                // 删除旧的
                if (null != bmp1) {
                    if (width != bmp1.getWidth() || height != bmp1.getHeight()) {
                        bmp1 = null;
                    }
                }

                // 创建新的
                if (null == bmp1) {
                    bmp1 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                }

                ByteBuffer buf = ByteBuffer.wrap(img);
                bmp1.copyPixelsFromBuffer(buf);
                // 这是在线程里操作的，千万不要直接在画布上绘制
                // 在surfaceView中显示
                if (null != surfaceView2.getHolder()) {
                    Canvas canvas = surfaceView2.getHolder().lockCanvas();

                    if (null != canvas) {
                        try {
                            Rect rt1 = new Rect(0, 0, surfaceView2.getWidth(), surfaceView2.getHeight());
                            Rect rt2 = new Rect(0, 0, width, height);
                            canvas.drawBitmap(bmp1, rt2, rt1, null);
                        } finally {
                            // 必须要释放，不然下次不会再绘制
                            surfaceView2.getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }else if ((micUsers.get(0).getMicindex() == mic2 && ssrc == (~micUsers.get(0).getUserid() + 0x1314)) ||
                    (micUsers.get(1).getMicindex() == mic2 && ssrc == (~micUsers.get(1).getUserid() + 0x1314)))
            {
//                if (textBackImage3.isShown()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textBackImage3.setVisibility(View.GONE);
//                            surfaceView3.setVisibility(View.VISIBLE);
//                        }
//                    });
//                }
                // 删除旧的
                if (null != bmp2) {
                    if (width != bmp2.getWidth() || height != bmp2.getHeight()) {
                        bmp2 = null;
                    }
                }

                // 创建新的
                if (null == bmp2) {
                    bmp2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                }

                ByteBuffer buf = ByteBuffer.wrap(img);
                bmp2.copyPixelsFromBuffer(buf);
                // 这是在线程里操作的，千万不要直接在画布上绘制
                // 在surfaceView中显示
                if (null != surfaceView3.getHolder()) {
                    Canvas canvas = surfaceView3.getHolder().lockCanvas();

                    if (null != canvas) {
                        try {
                            Rect rt1 = new Rect(0, 0, surfaceView3.getWidth(), surfaceView3.getHeight());
                            Rect rt2 = new Rect(0, 0, width, height);
                            canvas.drawBitmap(bmp2, rt2, rt1, null);
                        } finally {
                            // 必须要释放，不然下次不会再绘制
                            surfaceView3.getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }
        if (micUsers.size()==3) {
//            if (micUsers.get(0).getMicindex()==0){
//                buddyid = micUsers.get(0).getUserid();
//                toName = micUsers.get(0).getUseralias();
//                micid = micUsers.get(0).getUserid();
//            }else if (micUsers.get(1).getMicindex()==0){
//                buddyid = micUsers.get(1).getUserid();
//                toName = micUsers.get(1).getUseralias();
//                micid = micUsers.get(1).getUserid();
//            }else if (micUsers.get(2).getMicindex()==0){
//                buddyid = micUsers.get(2).getUserid();
//                toName = micUsers.get(2).getUseralias();
//                micid = micUsers.get(2).getUserid();
//            }
            if ((micUsers.get(0).getMicindex() == mic0 && ssrc == (~micUsers.get(0).getUserid() + 0x1314)) ||
                    (micUsers.get(1).getMicindex() == mic0 && ssrc == (~micUsers.get(1).getUserid() + 0x1314)) ||
                    (micUsers.get(2).getMicindex() == mic0 && ssrc == (~micUsers.get(2).getUserid() + 0x1314))) {
//                            textBackImage.setVisibility(View.GONE);
//                            surfaceView.setVisibility(View.VISIBLE);

                // 删除旧的
                if (null != bmp) {
                    if (width != bmp.getWidth() || height != bmp.getHeight()) {
                        bmp = null;
                    }
                }

                // 创建新的
                if (null == bmp) {
                    bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                }

                ByteBuffer buf = ByteBuffer.wrap(img);
                bmp.copyPixelsFromBuffer(buf);
                // 这是在线程里操作的，千万不要直接在画布上绘制
                // 在surfaceView中显示
                if (null != surfaceView.getHolder()) {
                    Canvas canvas = surfaceView.getHolder().lockCanvas();

                    if (null != canvas) {
                        try {
                            Rect rt1 = new Rect(0, 0, surfaceView.getWidth(), surfaceView.getHeight());
                            Rect rt2 = new Rect(0, 0, width, height);
                            canvas.drawBitmap(bmp, rt2, rt1, null);
                        } finally {
                            // 必须要释放，不然下次不会再绘制
                            surfaceView.getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }else if ((micUsers.get(0).getMicindex() == mic1 && ssrc == (~micUsers.get(0).getUserid() + 0x1314)) ||
                    (micUsers.get(1).getMicindex() == mic1 && ssrc == (~micUsers.get(1).getUserid() + 0x1314)) ||
                    (micUsers.get(2).getMicindex() == mic1 && ssrc == (~micUsers.get(2).getUserid() + 0x1314))){
//                if (textBackImage2.isShown()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textBackImage2.setVisibility(View.GONE);
//                            surfaceView2.setVisibility(View.VISIBLE);
//                        }
//                    });
//                }
                // 删除旧的
                if (null != bmp1) {
                    if (width != bmp1.getWidth() || height != bmp1.getHeight()) {
                        bmp1 = null;
                    }
                }

                // 创建新的
                if (null == bmp1) {
                    bmp1 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                }

                ByteBuffer buf = ByteBuffer.wrap(img);
                bmp1.copyPixelsFromBuffer(buf);
                // 这是在线程里操作的，千万不要直接在画布上绘制
                // 在surfaceView中显示
                if (null != surfaceView2.getHolder()) {
                    Canvas canvas = surfaceView2.getHolder().lockCanvas();

                    if (null != canvas) {
                        try {
                            Rect rt1 = new Rect(0, 0, surfaceView2.getWidth(), surfaceView2.getHeight());
                            Rect rt2 = new Rect(0, 0, width, height);
                            canvas.drawBitmap(bmp1, rt2, rt1, null);
                        } finally {
                            // 必须要释放，不然下次不会再绘制
                            surfaceView2.getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }else if ((micUsers.get(0).getMicindex() == mic2 && ssrc == (~micUsers.get(0).getUserid() + 0x1314)) ||
                    (micUsers.get(1).getMicindex() == mic2 && ssrc == (~micUsers.get(1).getUserid() + 0x1314)) ||
                    (micUsers.get(2).getMicindex() == mic2 && ssrc == (~micUsers.get(2).getUserid() + 0x1314)))
            {
//                if (textBackImage3.isShown()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            textBackImage3.setVisibility(View.GONE);
//                            surfaceView3.setVisibility(View.VISIBLE);
//                        }
//                    });
//                }
                // 删除旧的
                if (null != bmp2) {
                    if (width != bmp2.getWidth() || height != bmp2.getHeight()) {
                        bmp2 = null;
                    }
                }

                // 创建新的
                if (null == bmp2) {
                    bmp2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                }

                ByteBuffer buf = ByteBuffer.wrap(img);
                bmp2.copyPixelsFromBuffer(buf);
                // 这是在线程里操作的，千万不要直接在画布上绘制
                // 在surfaceView中显示
                if (null != surfaceView3.getHolder()) {
                    Canvas canvas = surfaceView3.getHolder().lockCanvas();

                    if (null != canvas) {
                        try {
                            Rect rt1 = new Rect(0, 0, surfaceView3.getWidth(), surfaceView3.getHeight());
                            Rect rt2 = new Rect(0, 0, width, height);
                            canvas.drawBitmap(bmp2, rt2, rt1, null);
                        } finally {
                            // 必须要释放，不然下次不会再绘制
                            surfaceView3.getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }



    }
    //音频接收播放
    @Override
    public void onAudio(int ssrc, int sample, int channel, byte[] pcm) {
//        System.out.println("========== onAudio: " + sample + ":" + channel + "(" + pcm.length + ")");
        if(false != mStop) {
            return;
        }
        if (play != null) {
            isplaying = true;
            play.setConfig(sample, channel);
            play.play(pcm);
        }
    }

    @Override
    public void onMic(String ip, int port, int rand, int uid) {
        if(false != mStop) {
            return;
        }
        mediaIp = ip;
        mediaPort = port;
        mediaRand = rand;
        //创建视频接收流
        // TODO Auto-generated method stub
        if(null == mgr) {
            mgr = new AVModuleMgr();
//            Log.d("123","mgr-----new--"+mgr);
            mgr.Init();
//            Log.d("123", "===uid" + uid);
            mgr.CreateRTPSession(0);
            mgr.SetServerAddr2(ip, port, 0);
            mgr.StartRTPSession();
            StartAV("", 0, 0, uid);
        }
    }
    //320086319
    @Override
    protected void onDestroy() {
//        Log.d("123","onDestory---");
        mStop = true;
        isRunning = false;
        super.onDestroy();
        if (mgr == null){

        }else {
            mgr.StopRTPSession();
            mgr.Uninit();
            play.stop();
        }
        EventBus.getDefault().unregister(this);
    }



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 50) {
//                Toast.makeText(RoomActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 50) {
//                Toast.makeText(RoomActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
            } else if(x1 - x2 > 50) {
                if (micUsers.size() != 1) {
/**
 *   if (micUsers.size()>1) {
 //                textBackImage.setVisibility(View.VISIBLE);
 //                surfaceView.setVisibility(View.GONE);
 //                textBackImage2.setVisibility(View.VISIBLE);
 //                surfaceView2.setVisibility(View.GONE);
 //                int a = mic0;
 //                mic0 = mic1;
 //                mic1 = a;
 ////                }
 */
                    if (micFlag == 0){
                        micFlag++;
//                        textBackImage.setVisibility(View.GONE);
                        surfaceView2.setVisibility(View.VISIBLE);
                        surfaceView.setVisibility(View.GONE);
                        surfaceView3.setVisibility(View.GONE);
//                    Toast.makeText(this, "暂无更多主播", Toast.LENGTH_SHORT).show();
                    }else if (micFlag == 1){
                        micFlag++;
                        surfaceView3.setVisibility(View.VISIBLE);
                        surfaceView.setVisibility(View.GONE);
                        surfaceView2.setVisibility(View.GONE);
                    }

//                    mgr.DelAudioStream(ssrc);
//                    mgr.DelVideoStream(ssrc);
//                    if (toid == micUsers.get(0).getUserid()) {
//                        buddyid = micUsers.get(1).getUserid();
//                        toName = micUsers.get(1).getUseralias();
//                        StartAV(mediaIp, mediaPort, mediaRand, micUsers.get(micUsers.size() - 1).getUserid());
//                    }
                }
//                Toast.makeText(RoomActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
            } else if(x2 - x1 > 50) {
                if (micFlag == 1){
                    micFlag--;
//                    textBackImage.setVisibility(View.VISIBLE);
                    surfaceView.setVisibility(View.VISIBLE);
                    surfaceView2.setVisibility(View.GONE);
                    surfaceView3.setVisibility(View.GONE);
//                    Toast.makeText(this, "暂无更多主播", Toast.LENGTH_SHORT).show();
                }else if (micFlag == 2){
                    micFlag--;

                    surfaceView2.setVisibility(View.VISIBLE);
                    surfaceView3.setVisibility(View.GONE);
                    surfaceView.setVisibility(View.GONE);
                }
//                if (micUsers.size() != 1) {
//                    mgr.DelAudioStream(ssrc);
//                    mgr.DelVideoStream(ssrc);
//                    if (toid == micUsers.get(1).getUserid()) {
//                        buddyid = micUsers.get(0).getUserid();
//                        toName = micUsers.get(0).getUseralias();
//                        StartAV(mediaIp, mediaPort, mediaRand, micUsers.get(0).getUserid());
//                    }
//                }
//                Toast.makeText(RoomActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
            }
            else {
                if (testController.isShown()){
                    testController.setVisibility(View.GONE);

                }else {
                    testController.setVisibility(View.VISIBLE);
                    AlphaAnimation animation1 = new AlphaAnimation(1.0f,0.0f);
                    animation1.setDuration(50 * 100);
                    animation1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            testController.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    testController.setAnimation(animation1);
                    animation1.start();
                }
            }
        }
        if (micUsers != null) {
            for (int i = 0; i < micUsers.size(); i++) {
                if (micUsers.get(i).getMicindex() == micFlag) {
                    giftToUser.setText(micUsers.get(i).getUseralias() + "(" + micUsers.get(i).getUserid() + ")");
//                            Log.d("123","toid---"+toid+"toName"+toName);
                }
            }
        }
        return true;
    }
}
