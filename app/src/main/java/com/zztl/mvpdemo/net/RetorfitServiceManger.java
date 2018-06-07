package com.zztl.mvpdemo.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zztl.mvpdemo.impl.Api;
import com.zztl.mvpdemo.storage.BlockChainPreference;
import com.zztl.mvpdemo.storage.PreferenceKeys;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @desc
 * @auth ${user}
 * @time 2018/3/14  16:49
 */

public class RetorfitServiceManger {
//    String BASE_URL = "http://zztl1.f3322.net:8888/app/";//测试服务器
            String BASE_URL = "https://xcb.bf258.com:4443/app/";//正式服务器
//        String BASE_URL = "http://192.168.20.134:8080";//曾服务器
//    String BASE_URL = "http://192.168.20.125:80/app/";//冯服务器
    private static final int DEFAULT_TIME_OUT = 30;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 30;

    private Retrofit mRetrofit;
    private Api mApi;

    private RetorfitServiceManger() {
        String token = (String) BlockChainPreference.getApp(PreferenceKeys.TOKEN.name(), "");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new StethoInterceptor());
        HttpCommonInterceptor httpCommonInterceptor = new HttpCommonInterceptor.Builder()
//                .addHeaderParams("appVersion", AppInfoUtils.getAppVersionName())
                .addHeaderParams("authorization", token).build();
        builder.addInterceptor(httpCommonInterceptor);
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(EncodeConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mApi = mRetrofit.create(Api.class);
    }

    private static class SingletonHolder {
        private static final RetorfitServiceManger INSTANCE = new RetorfitServiceManger();
    }

    public static RetorfitServiceManger getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Api getApiService() {
        return mApi;
    }

    public static <T> Observable<T> observe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    public static RequestBody parseRequestBody(String value) {
        return RequestBody.create(MediaType. parse("text/plain"), value);
    }

    public static MultipartBody.Part parseImageRequestBody(String key,File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData(key,file.getName(),requestBody);
    }

}
