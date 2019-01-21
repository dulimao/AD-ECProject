package com.ad.ad_core.net.download;

import com.ad.ad_core.net.ApiClient;
import com.ad.ad_core.net.ApiServieCreator;

public class DownloadHandler {

    private ApiClient.Builder mBuilder;
    public DownloadHandler(ApiClient.Builder builder){
        this.mBuilder = builder;
    }

    public void handleDownload(){
//        ApiServieCreator.getApiService().download(mBuilder.mUrl,mBuilder)
    }
}
