package com.ad.ad_core.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.ad.ad_core.app.ConfigLoader;

public class ADDimenUtil {

    public static int getScreenWidth(){
        Resources resources = ConfigLoader.getApplicationContext().getResources();
        DisplayMetrics dis = resources.getDisplayMetrics();
        return dis.widthPixels;
    }

    public static int getScreenHeight(){
        Resources resources = ConfigLoader.getApplicationContext().getResources();
        DisplayMetrics dis = resources.getDisplayMetrics();
        return dis.heightPixels;
    }

}
