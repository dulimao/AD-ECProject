package com.ad.myecproject;

import android.app.Application;

import com.ad.ad_core.app.Configrator;
import com.ad.ad_core.net.interceptors.DebugInterceptor;
import com.ad.ad_ecmodule.database.DatabaseManager;

public class ADApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Configrator.getInstance()
                .configContext(this)
                .configApiHost("http://127.0.0.1/")
                .configInterceptor(new DebugInterceptor("index",R.raw.user))
                .configWXAppId("")
                .configWXAppSecret("")
                .configure();
        DatabaseManager.getInstance().init(this);
    }
}
