package com.ad.myecproject;

import android.app.Application;

import com.ad.ad_core.Configrator;

public class ADApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Configrator.getInstance().configContext(this).configApiHost("http://127.0.0.1/").configure();
    }
}
