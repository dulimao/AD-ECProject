package com.ad.myecproject.generators;


import com.ad.ad_annotation.annotation.EntryGenerator;
import com.ad.ad_core.wechat.WXPayEntryTemplate;

@EntryGenerator(packName = "com.ad.myecproject",entryTemplate = WXPayEntryTemplate.class)
public interface WeCahtEntry {

}
