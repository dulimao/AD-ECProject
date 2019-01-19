package com.ad.ad_core;

import android.content.Context;

import java.util.HashMap;

/**
*@author 杜立茂
*@date 2019/1/19 18:04
*@description 配置类
*/
public class Configrator {
    private static final HashMap<String,Object> AD_CONFIGS = new HashMap<>();

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

    public void checkConfigurations(){
        boolean isReady = (boolean) AD_CONFIGS.get(ConfigType.CONFIG_READY);
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
