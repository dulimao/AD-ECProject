package com.ad.ad_ecmodule.fragment;

import android.graphics.Color;

import com.ad.ad_core.fragment.main.BaseBottomFragment;
import com.ad.ad_core.fragment.main.BottomItemFragment;
import com.ad.ad_core.fragment.main.BottomTabBean;
import com.ad.ad_ecmodule.R;

import java.util.LinkedHashMap;

public class MainFragment extends BaseBottomFragment {
    @Override
    protected LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(LinkedHashMap<BottomTabBean, BottomItemFragment> items) {
        items.put(new BottomTabBean(R.mipmap.wechat,"主页"),new IndexFragment());
        items.put(new BottomTabBean(R.mipmap.wechat,"分类"),new SortFragment());
        items.put(new BottomTabBean(R.mipmap.wechat,"发现"),new IndexFragment());
        items.put(new BottomTabBean(R.mipmap.wechat,"购物车"),new IndexFragment());
        items.put(new BottomTabBean(R.mipmap.wechat,"我的"),new IndexFragment());
        return items;
    }

    @Override
    protected int setIndexFragment() {
        return 0;
    }

    @Override
    protected int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
