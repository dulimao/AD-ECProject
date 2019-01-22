package com.ad.ad_core.net.interceptors;

import com.ad.ad_core.utils.ADFileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
*@author 杜立茂
*@date 2019/1/21 13:54
*@description 自定义拦截器，模拟网络请求返回数据
*/
public class DebugInterceptor extends BaseInterceptor {

    private String mDebugUrl;
    private int mDebugRawId;

    public DebugInterceptor(String debugUlr,int debugRawId){
        this.mDebugUrl = debugUlr;
        this.mDebugRawId = debugRawId;
    }

    private Response getResponse(Chain chain,String json){
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String url = chain.request().url().toString();
        if (url.contains(mDebugUrl)){
            String json = ADFileUtil.getRawFile(mDebugRawId);
            return getResponse(chain,json);
        }
        return chain.proceed(chain.request());
    }
}
