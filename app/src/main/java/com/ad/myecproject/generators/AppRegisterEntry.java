package com.ad.myecproject.generators;

import com.ad.ad_annotation.annotation.AppRegisterGenerator;
import com.ad.ad_core.wechat.generators.AppRegisterTemplate;

@AppRegisterGenerator(packName = "com.ad.myecproject",registerTemplate = AppRegisterTemplate.class)
public interface AppRegisterEntry {
}
