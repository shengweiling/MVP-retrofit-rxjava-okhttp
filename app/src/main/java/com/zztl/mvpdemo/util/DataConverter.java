package com.zztl.mvpdemo.util;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.zztl.mvpdemo.model.BaseResponseBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 转换数据
 * Created by yannable on 2018/1/31.
 */

public class DataConverter {

    //    private static Gson mGson = new GsonBuilder()
//            .registerTypeAdapter(BaseResponseBean.class, new BaseResponseBeanGsonDeserializer())
//            .create();
    private static Gson mGson = new Gson();

    //单例模式
    static DataConverter getInstance() {
        return DataConverter.Holder.INSTANCE;
    }

    //单例模式
    private static class Holder {
        private static final DataConverter INSTANCE = new DataConverter();
    }

    /**
     * 为后台返回定制的.避免非成功状态时data类型错误
     */
    public static <T> T getSingleBean(String jsonData, Class<T> clazz) throws JsonSyntaxException {
        String className = clazz.getSimpleName();
        String SuperclassName = clazz.getSuperclass().getSimpleName();
        if (className.equals("BaseResponseBean") || SuperclassName.equals("BaseResponseBean")) {
            BaseResponseBean baseBean = mGson.fromJson(jsonData, BaseResponseBean.class);

                return mGson.fromJson(jsonData, clazz);

        } else {
            return mGson.fromJson(jsonData, clazz);
        }

    }

    /**
     * 正常情况
     *
     * @throws Exception
     */
    public static <T> T getSingleBean2(String jsonData, Class<T> clazz) throws JsonSyntaxException {
        return mGson.fromJson(jsonData, clazz);
    }

   /* public static <T> T getSingleBean(String jsonData, GenericType<T> genericType) throws JsonSyntaxException {
        return mGson.fromJson(jsonData, genericType.getType());
    }
*/
    public static <T> T getSingleBean(String jsonData, Type type) throws JsonSyntaxException {
//        Type type = new TypeToken<BaseResponseBean<LoginBean>>() {
//        }.getType();
        return mGson.fromJson(jsonData, type);
    }

    /**
     * 功能描述：Object对象转化成json数据
     */
    public static String toJson(Object object) {
        String json = mGson.toJson(object);
        return json;
    }



    /**
     * json异常转换
     * 当data返回不是object的时候
     */
    public static class BaseResponseBeanGsonDeserializer implements JsonDeserializer<BaseResponseBean<?>> {
        @Override
        public BaseResponseBean deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

            final JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonElement jsonData = jsonObject.has("data") ? jsonObject.get("data") : null;

            BaseResponseBean httpResult = new BaseResponseBean();
            httpResult.setCode(jsonObject.has("code") ? jsonObject.get("code").getAsInt() : 200);
            httpResult.setMessage(jsonObject.has("msg") ? jsonObject.get("msg").getAsString() : "");
            //处理Data空串情况
            if (jsonData != null && jsonData.isJsonObject()) {
                //指定泛型具体类型
                if (type instanceof ParameterizedType) {
                    Type itemType = ((ParameterizedType) type).getActualTypeArguments()[0];
                    Object item = jsonDeserializationContext.deserialize(jsonData, itemType);
                    httpResult.setData(item);
                } else {
                    //未指定泛型具体类型
                    httpResult.setData(jsonData);
                }
            } else {
                httpResult.setData(null);
            }
            return httpResult;
        }
    }
}
