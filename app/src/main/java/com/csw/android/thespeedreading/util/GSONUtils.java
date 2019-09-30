package com.csw.android.thespeedreading.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * GSON解析JSONString实现
 * <P>Created by Caisw on 2017/5/8.</P>
 */
public class GSONUtils {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    /**
     * 将一个json字符串（"[{},{},,,,]"）解析成实体对象集合
     */
    public static <T> ArrayList<T> parseArray(String jsonStr, Class<T> clazz) {
        if (TextUtils.isEmpty(jsonStr)) {
            return null;
        }
        ArrayList<T> resultList = null;
        try {
            Type objectType = new TypeToken<ArrayList<JsonObject>>() {
            }.getType();
            List<JsonObject> joList = gson.fromJson(jsonStr, objectType);
            resultList = new ArrayList<>(joList.size());
            if (joList.size() > 0) {
                for (JsonObject jo : joList) {
                    resultList.add(gson.fromJson(jo, clazz));
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * 将一个json字符串（"{}"）解析成实体对象
     */
    public static <T> T parseObject(String jsonStr, Class<T> clazz) {
        T t = null;
        try {
            t = gson.fromJson(jsonStr, clazz);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将一个json字符串（"{}"）解析成实体对象
     */
    public static <T> T parseObject(String jsonStr, Type type) {
        T t = null;
        try {
            t = gson.fromJson(jsonStr, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * 将一个对象转成Json字符串
     */
    public static String toJSONString(Object obj) {
        if (obj == null) {
            return null;
        }
        return gson.toJson(obj);
    }
}
