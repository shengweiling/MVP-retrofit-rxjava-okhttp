package com.zztl.mvpdemo.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zztl.mvpdemo.util.MyActivityManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends RxAppCompatActivity {


    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);


        setTranslucentStatus();
//
        mBind = ButterKnife.bind(this);
        MyActivityManager.getInstance().pushOneActivity(this);

        initBase();


    }


    public int getStatusBarHeight() {
        int statusBarHeight = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }




    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void highApiEffects() {
        getWindow().getDecorView().setFitsSystemWindows(true);
        //透明状态栏 @顶部
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }


    /**
     * 设置状态栏颜色
     */
    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                attributes.flags |= flagTranslucentStatus;
                window.setAttributes(attributes);
            }
        }

    }


    protected abstract int setLayoutId();

    protected abstract void initBase();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /*if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }*/
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        mBind.unbind();
        super.onDestroy();

    }

    /**
     * skip to @param(cls)ï¼Œand call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Class<?> cls) {
        showActivity(aty, cls);
        finish();
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    /**
     * skip to @param(cls)ï¼Œand call @param(aty's) finish() method
     */
    public void skipActivity(Intent it) {
        showActivity(it);
        finish();
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    /**
     * skip to @param(cls)ï¼Œand call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        showActivity(aty, cls, extras);
        finish();
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    /**
     * show to @param(cls)ï¼Œbut can't finish activity
     */
    public void showActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        startActivity(intent);
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    /**
     * show to @param(cls)ï¼Œbut can't finish activity
     */
    public void showActivity(Intent it) {
        startActivity(it);
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    /**
     * show to @param(cls)ï¼Œbut can't finish activity
     */
    public void showActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        startActivity(intent);
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }


    /**
     * @param aty
     * @param cls
     * @param requestCode
     */
    public void showActivityForResult(Activity aty, Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        startActivityForResult(intent, requestCode);
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    /**
     * @param i
     * @param requestCode
     */
    public void showActivityForResult(Intent i, int requestCode) {
        startActivityForResult(i, requestCode);
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }



    @Override
    public void finish() {
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        MyActivityManager.getInstance().getActivityStack().remove(this);
        super.finish();

    }




}