package com.ad.ad_core.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallBack implements Callback<String> {


    private final IRequest mRequest;
    private final ISuccess mSuccess;
    private final IError mError;
    private final IFailure mFailure;

    public ApiCallBack(IRequest mRequest, ISuccess mSuccess, IError mError, IFailure mFailure) {
        this.mRequest = mRequest;
        this.mSuccess = mSuccess;
        this.mError = mError;
        this.mFailure = mFailure;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (mSuccess != null){
                    mSuccess.onSuccess(response.body());
                }
            }
        }else {
            if (mError != null){
                mError.onError(response.code(),response.message());
            }
        }
        if (mRequest != null){
            mRequest.requetEnd();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (mFailure != null){
            mFailure.onFailure(t.getMessage());
        }
        if (mRequest != null){
            mRequest.requetEnd();
        }
    }
}
