package com.ad.ad_core.net.download;

import android.os.AsyncTask;

import com.ad.ad_core.net.callback.IError;
import com.ad.ad_core.net.callback.IFailure;
import com.ad.ad_core.net.callback.ISuccess;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiDownloadCallback implements Callback<ResponseBody> {


    private ISuccess mSuccess;
    private IError mError;
    private IFailure mFailure;
    private String mDownloadUrl;
    private String mExtension;
    private String mName;

    public ApiDownloadCallback(ISuccess success, IError error, IFailure failure,String downloadUrl,String extension,String name){
        this.mSuccess = success;
        this.mError = error;
        this.mFailure = failure;
        this.mDownloadUrl = downloadUrl;
        this.mExtension = extension;
        this.mName = name;
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()){
            SaveFileAsyncTask asyncTask = new SaveFileAsyncTask(mSuccess);
            ResponseBody body = response.body();
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,mDownloadUrl,mExtension,mName,body);
        }else{
            if (mError != null){
                mError.onError(response.code(),response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
            if (mFailure != null){
                mFailure.onFailure(t.getMessage());
            }
    }
}
