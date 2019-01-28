package com.ad.ad_core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

public class MultipleItemEntity implements MultiItemEntity {

    private ReferenceQueue<LinkedHashMap<Object,Object>> mItemQueue = new ReferenceQueue<>();
    private LinkedHashMap<Object,Object> mMultipleFields = new LinkedHashMap<>();
    private SoftReference<LinkedHashMap<Object,Object>> mFieldsReference = new SoftReference<>(mMultipleFields,mItemQueue);

    private static MultipleItemEntity multipleItemEntity;

    @Override
    public int getItemType() {
        return (int) mFieldsReference.get().get(MultipleFields.item_type);
    }

    @SuppressWarnings("unchecked")
    public <T> T getField(Object key){
        return (T) mFieldsReference.get().get(key);
    }

    public LinkedHashMap<?,?> getFields(){
        return mFieldsReference.get();
    }

    public static MultipleItemEntity creator(){
        multipleItemEntity = new MultipleItemEntity();
        multipleItemEntity.mFieldsReference.get().clear();
        return multipleItemEntity;
    }

    public MultipleItemEntity setItemType(int type){
        multipleItemEntity.mFieldsReference.get().put(MultipleFields.item_type,type);
        return multipleItemEntity;
    }

    public MultipleItemEntity setField(Object key,Object value){
        multipleItemEntity.mFieldsReference.get().put(key,value);
        return multipleItemEntity;
    }

    public MultipleItemEntity create(){
        return multipleItemEntity;
    }


}
