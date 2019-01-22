package com.ad.ad_core.wechat;

import android.app.Activity;
import android.content.Context;

import com.ad.ad_core.app.Configrator;
import com.ad.ad_core.app.LoadConfig;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
*@author 杜立茂
*@date 2019/1/22 17:10
*@description 微信客户端
*/
public class WeChatClient {
    public static String mWxAppId = LoadConfig.getWxAppId();
    public static String mWxAppSecret = LoadConfig.getWxAppSecret();

    private IWeChatSignInCallback mWeChatSignCallback;

    private IWXAPI mIWXAPI;

    private static final class WeChatClientHolder{
        private static final WeChatClient instance = new WeChatClient();
    }

    public static WeChatClient getInstance(){
        return WeChatClientHolder.instance;
    }

    private WeChatClient(){
        Context context = LoadConfig.getActivityContext();
        mIWXAPI = WXAPIFactory.createWXAPI(context,mWxAppId,false);
    }

    public final IWXAPI getWXAPI(){
        return mIWXAPI;
    }

    public final void signIn(){
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        mIWXAPI.sendReq(req);
    }

    public WeChatClient onSignSuccess(IWeChatSignInCallback iWeChatSignInCallback){
        this.mWeChatSignCallback = iWeChatSignInCallback;
        return this;
    }


    public IWeChatSignInCallback getWechatSignCallback(){
        return this.mWeChatSignCallback;
    }


}
