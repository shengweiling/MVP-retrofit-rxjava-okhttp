package com.zztl.mvpdemo.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;



/**
 * @desc
 * @auth ${user}
 * @time 2018/6/7  10:51
 */
public class EncodeConverterFactory extends Converter.Factory {
    public static EncodeConverterFactory create() {
        return create(new Gson());
    }


    public static EncodeConverterFactory create(Gson gson) {
        return new EncodeConverterFactory(gson);
    }

    private final Gson gson;

    private EncodeConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new ResponseBodyConverter<>(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new EncodeRequestConverter<>(gson, adapter);
    }
}
