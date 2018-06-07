package com.zztl.mvpdemo.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.zztl.mvpdemo.util.RSAUtils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @desc
 * @auth ${user}
 * @time 2018/6/7  10:32
 */
public class EncodeRequestConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private final TypeAdapter<T> adapter;
    private final Gson gson;

    public EncodeRequestConverter(Gson gson,TypeAdapter<T> adapter) {
        this.adapter = adapter;
        this.gson = gson;
    }


    @Override
    public RequestBody convert(T value) throws IOException {
        try {
            return RequestBody.create(MEDIA_TYPE, RSAUtils.encryptByPublicKey(value.toString()).replaceAll("[\\s*\t\n\r]", ""));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
