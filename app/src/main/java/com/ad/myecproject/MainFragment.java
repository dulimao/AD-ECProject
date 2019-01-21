package com.ad.myecproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ad.ad_core.fragment.AD_Fragment;
import com.ad.ad_core.net.ApiClient;
import com.ad.ad_core.net.callback.IError;
import com.ad.ad_core.net.callback.IFailure;
import com.ad.ad_core.net.callback.ISuccess;
import com.ad.ad_core.ui.AVLoader;

public class MainFragment extends AD_Fragment {

    Button btnTest;

    @Override
    public Object setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        btnTest = rootView.findViewById(R.id.button_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AVLoader.showLoader(getContext());
//                ApiClient.newBuilder()
//                        .url("http://baidu.com/index")
//                        .formBody("username","adu")
//                        .success(new ISuccess() {
//                            @Override
//                            public void onSuccess(String response) {
//                                Log.d("MainFragment", "onSuccess(): " + response);
//                                AVLoader.stopLoader();
//                            }
//                        }).failure(new IFailure() {
//                    @Override
//                    public void onFailure(String msg) {
//                        Log.e("MainFragment", "onFailure(): " + msg);
//                        AVLoader.stopLoader();
//                    }
//                }).error(new IError() {
//                    @Override
//                    public void onError(int errorCode, String errMsg) {
//                        AVLoader.stopLoader();
//                        Log.e("MainFragment", "onError() errorCode: " + errorCode + " errorMsg : " + errMsg);
//                    }
//                }).build().get();
                ApiClient.newBuilder()
                        .url("https://www.baidu.com/img/bd_logo1.png?where=super")
                        .downloadDir("adu")
                        .downloadExtension(".jpg")
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                Log.d("MainFragment","onSuccess():" + response);
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure(String msg) {
                                Log.d("MainFragment","onFailure(): " + msg);
                            }
                        })
                        .build()
                        .download();
            }
        });

    }
}
