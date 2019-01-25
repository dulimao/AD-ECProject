package com.ad.ad_core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.util.LinkedHashMap;

public class MultipleItemEntity implements MultiItemEntity {

    private ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE = new ReferenceQueue<>();

    @Override
    public int getItemType() {
        return 0;
    }
}
