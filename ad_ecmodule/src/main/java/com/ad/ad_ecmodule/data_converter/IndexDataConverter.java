package com.ad.ad_ecmodule.data_converter;

import com.ad.ad_core.ui.recycler.DataConverter;
import com.ad.ad_core.ui.recycler.MultipleFields;
import com.ad.ad_core.ui.recycler.MultipleItemEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class IndexDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        JSONArray jsonArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = jsonArray.size();
        for (int i = 0; i < size; i++){
            JSONObject object = jsonArray.getJSONObject(i);
            String text = object.getString("address");
            String imageUrl = object.getString("avatar");
            String id = object.getString("userId");
            JSONArray bannerArray = object.getJSONArray("banners");
            int type = 0;
            ArrayList<String> banners = new ArrayList<>(5);
            if (imageUrl == null && text != null){
                type = 1;
            }else if (imageUrl != null && text == null){
                type = 2;
            }else {
                type = 3;

                int bannerSize = bannerArray.size();
                for (int j = 0; j < bannerSize; j++){
                    bannerArray.add(bannerArray.getString(j));
                }
            }

            MultipleItemEntity entity = MultipleItemEntity.creator()
                    .setField(MultipleFields.item_type,type)
                    .setField(MultipleFields.text,text)
                    .setField(MultipleFields.image,imageUrl)
                    .setField(MultipleFields.id,id)
                    .setField(MultipleFields.banner,banners)
                    .create();
            mEntitys.add(entity);

        }
        return mEntitys;
    }
}
