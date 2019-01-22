package com.ad.myecproject.generators;


import com.ad.ad_annotation.annotation.EntryGenerator;
import com.ad.ad_core.wechat.generators.WXEntryTemplate;

@EntryGenerator(packName = "com.ad.myecproject",entryTemplate = WXEntryTemplate.class)
public interface WeCahtEntry {

}
