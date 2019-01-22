package com.ad.ad_core.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
*@author 杜立茂
*@date 2019/1/19 18:04
*@description 配置类
*/
public class Configrator {
    //存放配置项
    private static final HashMap<String,Object> AD_CONFIGS = new HashMap<>(10);
    //存放Okhttp拦截器
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>(2);
    private static class ConfigratorHolder{
        private static Configrator instance = new Configrator();
    }

    public static Configrator getInstance(){
        return ConfigratorHolder.instance;
    }

    private Configrator(){
        AD_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    public void configure(){
        AD_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public Configrator configApiHost(String host){
        AD_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    public Configrator configContext(Context context){
        AD_CONFIGS.put(ConfigType.APPLICATION_CONTEXT.name(),context);
        return this;
    }

    public Configrator configWXAppId(String appId){
        AD_CONFIGS.put(ConfigType.WX_APP_ID.name(),appId);
        return this;
    }

    public Configrator configWXAppSecret(String appSecret){
        AD_CONFIGS.put(ConfigType.WX_APP_SECRET.name(),appSecret);
        return this;
    }

    public Configrator configActivityContext(Context context){
        AD_CONFIGS.put(ConfigType.ACTIVITY_CONTEXT.name(),context);
        return this;
    }

    public Configrator configInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        return this;
    }

    public Configrator configInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        return this;
    }



    public void checkConfigurations(){
        boolean isReady = (boolean) AD_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("configuration is not complete");
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfigration(Enum<ConfigType> key){
        checkConfigurations();
        return (T) AD_CONFIGS.get(key.name());
    }
}
