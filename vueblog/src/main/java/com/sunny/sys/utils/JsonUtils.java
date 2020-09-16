package com.sunny.sys.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Sunny
 * @Date: 2020/6/15
 * @Description:
 */
public final class JsonUtils {
    /**
     * 对象转换成json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //注册自定义String的适配器 将null转成""
//        gsonBuilder.registerTypeAdapter(String.class, new StringNullAdapter());
//        Gson gson = gsonBuilder.create();
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(obj);
    }

    /**
     * json字符串转成对象
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }


    public static List<Map<String,Object>> fromJsonToList(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, new TypeToken<ArrayList<Map<String,Object>>>(){}.getType());
    }


    public static Map<String,Object> fromJsonToMap(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, new TypeToken<Map<String,Object>>(){}.getType());
    }

}
