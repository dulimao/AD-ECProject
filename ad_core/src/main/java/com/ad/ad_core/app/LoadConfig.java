package com.ad.ad_core.app;

import android.content.Context;

import java.util.List;

import okhttp3.Interceptor;

public class LoadConfig {

    public static Context getApplicationContext(){
        return Configrator.getInstance().getConfigration(ConfigType.APPLICATION_CONTEXT);
    }

    public static Context getActivityContext(){
        return Configrator.getInstance().getConfigration(ConfigType.ACTIVITY_CONTEXT);
    }

    public static List<Interceptor> getInterceptors(){
        return Configrator.getInstance().getInterceptors();
    }

    public static String getWxAppId(){
        return Configrator.getInstance().getConfigration(ConfigType.WX_APP_ID);
    }

    public static String getWxAppSecret(){
        return Configrator.getInstance().getConfigration(ConfigType.WX_APP_SECRET);
    }





}
