package com.ad.ad_core.net;

import android.print.PrinterId;

import com.ad.ad_core.app.ConfigType;
import com.ad.ad_core.app.Configrator;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
*@author 杜立茂
*@date 2019/1/20 14:08
*@description Api创建
*/
public class ApiServieCreator {

    public static ApiService getApiService(){
        return ApiServiceHolder.API_SERVICE;
    }

    private static final class RetrofitHolder{
        private static final String BASE_URL = Configrator.getInstance().getConfigration(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClientHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpClientHolder{
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.SECONDS)
                .readTimeout(10000,TimeUnit.SECONDS)
                .build();
    }

    private static final class ApiServiceHolder{
        private static final ApiService API_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(ApiService.class);
    }
}
