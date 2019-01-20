package com.ad.ad_core.ui;

import android.content.Context;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.HashMap;

public class LoaderCreator {
    private static final HashMap<String, Indicator> LOADER_MAP = new HashMap<>(5);

    public static AVLoadingIndicatorView create(String type, Context context){
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADER_MAP.get(type) == null){
            Indicator indicator = getIndicator(type);
            LOADER_MAP.put(type,indicator);//缓存下来，提高性能
        }
        avLoadingIndicatorView.setIndicator(LOADER_MAP.get(type));
        return avLoadingIndicatorView;
    }


    private static Indicator getIndicator(String name){
        if (TextUtils.isEmpty(name)){
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (!name.contains(".")){
                String defultPackName = AVLoadingIndicatorView.class.getPackage().getName();
                sb.append(defultPackName).append(".indicators").append(".");
        }
        sb.append(name);
        try {
            Class<?> clazz = Class.forName(sb.toString());
            return (Indicator) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
