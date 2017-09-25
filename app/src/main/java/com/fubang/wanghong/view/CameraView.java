package com.fubang.wanghong.view;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

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
 * 创建时间：2017-03-19 13:05
 * 修改人：zhuyunjian
 * 修改时间：2017-03-19 13:05
 * 修改备注：
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback{
    Camera camera;
    private SurfaceHolder holder;
    private boolean isPreviewing;


    public CameraView(Context context) {
        super(context);
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initCamera();
        initHolder();
    }

    private void initCamera() {
        camera = Camera.open();
        camera.setDisplayOrientation(90);
//        camera.setPreviewDisplay(holder);
    }
    //开始预览
    private void startPreView(){
        if (!isPreviewing){
            camera.startPreview();
            isPreviewing = true;
        }
    }

    //停止预览
    public void stopPreview(){
        if (isPreviewing && camera != null){
            camera.stopPreview();
            isPreviewing = false;
        }
    }

    private void initHolder(){
        holder = getHolder();
        holder.addCallback(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        startPreView();
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        stopPreview();


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {


    }
}
