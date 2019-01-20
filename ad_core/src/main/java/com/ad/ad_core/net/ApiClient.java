package com.ad.ad_core.net;

import com.ad.ad_core.net.callback.ApiCallBack;
import com.ad.ad_core.net.callback.IError;
import com.ad.ad_core.net.callback.IFailure;
import com.ad.ad_core.net.callback.IRequest;
import com.ad.ad_core.net.callback.ISuccess;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ApiClient {

    private final String mUrl;
    private final Map<String, Object> mParams;
    private final RequestBody mRequestBody;
    private final IRequest mRequest;
    private final ISuccess mSuccess;
    private final IError mError;
    private final IFailure mFailure;


    private ApiClient(Builder builder) {
        this.mUrl = builder.mUrl;
        this.mParams = builder.mParams;
        this.mRequestBody = builder.mRequestBody;
        this.mRequest = builder.mRequest;
        this.mSuccess = builder.mSuccess;
        this.mError = builder.mError;
        this.mFailure = builder.mFailure;
    }


    public static Builder newBuilder() {
        return new Builder();
    }

    private void request(HttpMethod method) {
        ApiService apiService = ApiServieCreator.getApiService();
        Call<String> call = null;
        if (mRequest != null) {
            mRequest.requetStart();
        }

        switch (method) {
            case GET:
                call = apiService.get(mUrl,mParams);
                break;
            case POST:
                call = apiService.post(mUrl,mParams);
                break;
            case PUT:
                call = apiService.put(mUrl,mParams);
                break;
            case DELETE:
                call = apiService.delete(mUrl,mParams);
                break;
            default:
                break;
        }

        if (call != null){
            call.enqueue(new ApiCallBack(mRequest,mSuccess,mError,mFailure));
        }
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public static class Builder {

        private String mUrl;
        private Map<String, Object> mParams;
        private RequestBody mRequestBody;
        private IRequest mRequest;
        private ISuccess mSuccess;
        private IError mError;
        private IFailure mFailure;

        Builder() {
        }

        public Builder url(String url) {
            this.mUrl = url;
            return this;
        }

        public Builder params(Map<String, Object> params) {
            this.mParams = params;
            return this;
        }

        public Builder params(String key, Object obj) {
            if (this.mParams == null) {
                this.mParams = new HashMap<>();
            }
            this.mParams.put(key, obj);
            return this;
        }

        public Builder raw(String content) {
            this.mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), content);
            return this;
        }

        public Builder onRequest(IRequest iRequest) {
            this.mRequest = iRequest;
            return this;
        }

        public Builder success(ISuccess iSuccess) {
            this.mSuccess = iSuccess;
            return this;
        }

        public Builder error(IError iError) {
            this.mError = iError;
            return this;
        }

        public Builder failure(IFailure iFailure) {
            this.mFailure = iFailure;
            return this;
        }

        public Map<String, Object> checkParams() {
            if (this.mParams == null) {
                this.mParams = new HashMap<>();
            }
            return mParams;
        }


        public ApiClient build() {
            return new ApiClient(this);
        }
    }
}
