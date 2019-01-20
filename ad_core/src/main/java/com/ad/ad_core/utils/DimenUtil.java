package com.ad.ad_core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.ad.ad_core.app.LoadConfig;

public class DimenUtil {

    public static int getScreenWidth(){
        Resources resources = LoadConfig.getApplication().getResources();
        DisplayMetrics dis = resources.getDisplayMetrics();
        return dis.widthPixels;
    }

    public static int getScreenHeight(){
        Resources resources = LoadConfig.getApplication().getResources();
        DisplayMetrics dis = resources.getDisplayMetrics();
        return dis.heightPixels;
    }

}
