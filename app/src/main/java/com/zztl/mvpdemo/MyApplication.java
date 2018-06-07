package com.zztl.mvpdemo;

import android.app.Application;
import android.content.Context;

/**
 * @desc
 * @auth ${user}
 * @time 2018/6/4  11:27
 */
public class MyApplication extends Application {
    private static MyApplication mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
//        Stetho.initializeWithDefaults(this);
        if (null == mInstance) {
            mInstance = this;
        }

    }

    public static Context getContext() {
        return mInstance;
    }
}
