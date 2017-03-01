package com.fubang.wanghong.fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.fubang.wanghong.AppConstant;
import com.fubang.wanghong.MainActivity;
import com.fubang.wanghong.R;
import com.fubang.wanghong.adapters.HomeTitleAdapter;
import com.fubang.wanghong.ui.HistoryActivity_;
import com.fubang.wanghong.ui.SearchActivity_;
import com.fubang.wanghong.ui.SplashActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.decoding.Intents;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//import info.vividcode.android.zxing.CaptureActivity;
//import info.vividcode.android.zxing.Intents;

import kr.co.namee.permissiongen.PermissionGen;


/**
 * 首页
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    @ViewById(R.id.home_viewpager)
    ViewPager viewPager;
    @ViewById(R.id.home_tablayout)
    TabLayout tabLayout;
    @ViewById(R.id.main_head_icon)
    ImageView iconImage;
    @ViewById(R.id.main_head_scanner)
    ImageView scannerImage;
    @ViewById(R.id.main_head_search)
    FrameLayout searchImage;
    @ViewById(R.id.main_head_history)
    ImageView historyImage;
    @ViewById(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    @Override
    public void initView() {
        localImages.add(getResId("banner1",R.mipmap.class));
        localImages.add(getResId("banner1",R.mipmap.class));
        localImages.add(getResId("banner1",R.mipmap.class));
//        localImages.add("http://61.153.111.94:9418/home_hjktv/Tpl/default/Room/ad/1.jpg");
//        localImages.add("http://61.153.111.94:9418/home_hjktv/Tpl/default/Room/ad/2.jpg");
//        localImages.add("http://61.153.111.94:9418/home_hjktv/Tpl/default/Room/ad/3.jpg");
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator,R.mipmap.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        convenientBanner.startTurning(2000);
        for (int i = 0; i < AppConstant.HOME_TYPE_TITLE.length; i++) {
            titles.add(AppConstant.HOME_TYPE_TITLE[i]);
        }
        fragments.add(HomeItemFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(0)).build());
//        fragments.add(HotFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(0)).build());
//        fragments.add(NewFragment_.builder().arg(AppConstant.HOME_TYPE,titles.get(1)).build());
        scannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //google官方扫描二维码
//                new IntentIntegrator(getActivity()).initiateScan();
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                    PermissionGen.with(getActivity())
                            .addRequestCode(100)
                            .permissions(
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.RECORD_AUDIO)

                            .request();
//
                }else {
                    //第三方的扫描
                    Intent intent = new Intent(getContext(), CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
//                //第三方的扫描
//                Intent intent = new Intent(getContext(), CaptureActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
//                startActivityForResult(new Intent(getContext(), CaptureActivity.class),0);
            }
        });
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SearchActivity_.intent(getContext()).get());
            }
        });
        historyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(HistoryActivity_.intent(getContext()).get());
            }
        });
    }


    @Override
    public void initData() {
        HomeTitleAdapter adapter = new HomeTitleAdapter(getChildFragmentManager(),fragments,titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        String contents = intentResult.getContents();
//        if (resultCode == getActivity().RESULT_OK){
        if (requestCode == REQUEST_CODE){
//            String scan_result = data.getStringExtra(Intents.Scan.RESULT);
//            Toast.makeText(getContext(), scan_result, Toast.LENGTH_SHORT).show();
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }else {
            Toast.makeText(getContext(), "扫描失败", Toast.LENGTH_SHORT).show();
        }
    }
    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
//            GenericDraweeHierarchyBuilder builder =
//                    new GenericDraweeHierarchyBuilder(getResources());
//            GenericDraweeHierarchy hierarchy = builder
//                    .setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY)
//                    .setPlaceholderImage(getContext().getResources().getDrawable(R.mipmap.ktv_background))
//                    .build();
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.setHierarchy(hierarchy);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
//            DraweeController controller = Fresco.newDraweeControllerBuilder()
//                    .setUri(Uri.parse(data))
//                    .setAutoPlayAnimations(true)
//                    .build();
//            imageView.setController(controller);
        }
    }
    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     *
     * @param variableName
     * @param c
     * @return
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
