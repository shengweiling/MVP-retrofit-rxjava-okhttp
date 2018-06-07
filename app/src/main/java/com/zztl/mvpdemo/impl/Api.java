package com.zztl.mvpdemo.impl;

import com.zztl.mvpdemo.model.BaseResponseBean;
import com.zztl.mvpdemo.model.LoginBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * @desc
 * @auth ${user}
 * @time 2018/6/4  11:51
 */
public interface Api {
    @POST("user/login")
    Observable<LoginBean> login(@Body String loginRequest);


    @Multipart
    @POST("user/photo")
    Observable<BaseResponseBean> upload( @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST("user/photo")
    Observable<BaseResponseBean> uploadImage(@Part MultipartBody.Part part, @Part(RequestKeys.FTILETYPE) RequestBody type);
}
