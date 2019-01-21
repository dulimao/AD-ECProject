package com.ad.ad_core.net.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
*@author 杜立茂
*@date 2019/1/21 12:41
*@description 自定义拦截器
*/
public abstract class BaseInterceptor implements Interceptor {

    /**
     * 获取GET请求URL中的参数
     * @param chain
     * @return
     */
    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
        HttpUrl httpUrl = chain.request().url();
        int size = httpUrl.querySize();
        LinkedHashMap<String,String> parameters = new LinkedHashMap<>();
        for (int i = 0; i < size; i++){
            parameters.put(httpUrl.queryParameterName(i),httpUrl.queryParameterValue(i));
        }
        return parameters;
    }

    protected String getUrlParameters(Chain chain,String key){
        Request request = chain.request();
        return request.url().queryParameter(key);
    }


    /**
     * 获取Body参数
     * @param chain
     * @return
     */
    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
        FormBody formBody = (FormBody) chain.request().body();
        LinkedHashMap<String,String> parameters = new LinkedHashMap<>();
        if (formBody != null && formBody.size() != 0){
            for (int i = 0; i < formBody.size(); i++){
                parameters.put(formBody.name(i),formBody.value(i));
            }
        }
        return parameters;
    }

    protected String getBodyParameters(Chain chain,String key){
        return getBodyParameters(chain).get(key);
    }

}
