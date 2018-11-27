package com.example.cne_shop.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.example.cne_shop.bean.UserInfo;
import com.example.cne_shop.utils.UserInfoLocalData;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 博 on 2017/8/23.
 */

public class MyApplicationInfo extends Application {

    public static final int START_FOR_RESULT  = 0 ;
    public static final int START_NO_RESULT  = 1 ;

    private UserInfo userInfo ;
    private String token ;
    private static Intent intent ;
    private static  int startIntentStype ;

    public static int getStartIntentStype() {
        return startIntentStype;
    }

    public static void setStartIntentStype(int startIntentStype) {
        MyApplicationInfo.startIntentStype = startIntentStype;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public static MyApplicationInfo myApplicationInfo = null;

    public static MyApplicationInfo getInstance(){
        if (myApplicationInfo == null)
            myApplicationInfo = new MyApplicationInfo();
        return myApplicationInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //this.myApplicationInfo = this ;
        initUser();
        Fresco.initialize(this);
        //NineGridView.setImageLoader(new PicassoImageLoader());

    }

    public void initUser(){
        this.userInfo = UserInfoLocalData.getUser(this );
        this.token = UserInfoLocalData.getToken(this) ;
    }

    public UserInfo getUser(){
        return userInfo ;
    }

    public String getToken(){
        return token ;
    }

    public void putUser(UserInfo user , String token){

        this.token = token ;
        this.userInfo = user ;
        UserInfoLocalData.putUser(this , userInfo);
        UserInfoLocalData.putToken(this , token);
    }

    public void clearUser(){
        this.userInfo = null ;
        this.token = null ;
        UserInfoLocalData.clearUser(this);
        UserInfoLocalData.clearToken(this);
    }

    public static void jumpToTargeActivity(Activity activity){
        activity.startActivity(intent);
        intent = null ;
    }
}
