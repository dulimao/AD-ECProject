package com.ad.ad_core.fragment.main;

import java.util.LinkedHashMap;

/**
*@author 杜立茂
*@date 2019/1/24 10:50
*@description 将底部导航键和对应的Fragment结合起来
*/
public class MainItemCreator {

    private final LinkedHashMap<BottomTabBean,BottomItemFragment> mTabAndFragments = new LinkedHashMap<>(5);

    public static MainItemCreator creator(){
        return new MainItemCreator();
    }

    private void addItem(BottomTabBean tabBean,BottomItemFragment itemFragment){
        mTabAndFragments.put(tabBean,itemFragment);
    }

    private void addItems(LinkedHashMap<BottomTabBean,BottomItemFragment> items){
        mTabAndFragments.putAll(items);
    }

    public LinkedHashMap<BottomTabBean,BottomItemFragment> create(){
        return mTabAndFragments;
    }

}
