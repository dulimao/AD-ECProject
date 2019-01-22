package com.ad.ad_core.wechat.generators;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ad.ad_core.wechat.BaseWXEntryActivity;
import com.ad.ad_core.wechat.IWeChatSignInCallback;
import com.ad.ad_core.wechat.WeChatClient;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置成透明，等再次进入就finish掉，并且不需要动画
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String resp) {
        WeChatClient.getInstance().getWechatSignCallback().onSignInSuccess(resp);
    }
}
