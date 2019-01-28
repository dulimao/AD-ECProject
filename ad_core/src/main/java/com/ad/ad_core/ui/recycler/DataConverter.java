package com.ad.ad_core.ui.recycler;

import java.util.ArrayList;

public abstract class DataConverter {

    protected ArrayList<MultipleItemEntity> mEntitys = new ArrayList<>();

    private String mJsonData;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String data){
        this.mJsonData = data;
        return this;
    }

    protected String getJsonData(){
        if (mJsonData == null || mJsonData.equals("")){
            throw new NullPointerException("data is null !");
        }
        return mJsonData;
    }

    public void clear(){
        mEntitys.clear();
    }

}
