package com.ad.ad_core.wechat;

import com.ad.ad_core.net.ApiClient;
import com.ad.ad_core.net.callback.ISuccess;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

// TODO: 2019/1/22 重构成rxjava后再修改链式的多个网络请求，现在的嵌套太多
public abstract class BaseWXEntryActivity extends BaseWXActivity {


    protected abstract void onSignInSuccess(String resp);

    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        String code = ((SendAuth.Resp)baseResp).code;
        StringBuilder authUrl = new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(WeChatClient.mWxAppId)
                .append("&secret=")
                .append(WeChatClient.mWxAppSecret)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");
        getAuth(authUrl.toString());
    }

    private void getAuth(String url){
        ApiClient.newBuilder().url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        String accessToken = jsonObject.getString("access_token");
                        String openId = jsonObject.getString("openid");
                        StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl.append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");

                    }
                })
                .build().get();
    }

    //获取最终的用户信息
    private void getUserInfo(String url){
        ApiClient.newBuilder().url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response);
                    }
                })
                .build()
                .get();
    }

}
