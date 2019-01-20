package com.ad.ad_core.app;

import android.content.Context;

public class LoadConfig {

    public static Context getApplication(){
        return Configrator.getInstance().getConfigration(ConfigType.APPLICATION_CONTEXT);
    }



}
