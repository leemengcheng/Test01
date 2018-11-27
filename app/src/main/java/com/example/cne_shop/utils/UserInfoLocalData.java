package com.example.cne_shop.utils;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.example.cne_shop.bean.UserInfo;
import com.example.cne_shop.contents.Contents;

/**
 * Created by 博 on 2017/7/23.
 */

public class UserInfoLocalData {

    public static void putUser(Context context , UserInfo user){

        String user_json = JsonUtil.toJSON(user) ;
        PreferenceUtil.putString(context , Contents.USER_JSON , user_json);
    }

    public static void putToken(Context context , String token){

        PreferenceUtil.putString(context , Contents.TOKEN , token);
    }

    public static UserInfo getUser(Context context){
        String user_json = PreferenceUtil.getString(context , Contents.USER_JSON , null);
        return JSONObject.parseObject(user_json, UserInfo.class);
    }

    public static String getToken(Context context){
        String token = PreferenceUtil.getString(context , Contents.TOKEN , null);
        return JSONObject.parseObject(token, String.class);
    }

    public static void clearUser(Context context){
        PreferenceUtil.putString(context , Contents.USER_JSON , "");
    }

    public static void clearToken(Context context){
        PreferenceUtil.putString(context , Contents.TOKEN , "");
    }

}
