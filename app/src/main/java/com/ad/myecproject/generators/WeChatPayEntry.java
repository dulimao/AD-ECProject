package com.ad.myecproject.generators;

import com.ad.ad_annotation.annotation.PayEntryGenerator;
import com.ad.ad_core.wechat.WXPayEntryTemplate;

@PayEntryGenerator(packName = "com.ad.myecproject",payEntryTemplate = WXPayEntryTemplate.class)
public interface WeChatPayEntry {
}
