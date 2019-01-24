package com.ad.ad_core.fragment.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ad.ad_core.R;
import com.ad.ad_core.R2;
import com.ad.ad_core.fragment.AD_Fragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
*@author 杜立茂
*@date 2019/1/23 9:14
*@description 底部导航
*/
public abstract class BaseBottomFragment extends AD_Fragment implements View.OnClickListener {
    private ArrayList<BottomTabBean> mItemTabBeans = new ArrayList<>(5);
    private ArrayList<BottomItemFragment> mItemFragments = new ArrayList<>(5);
    private LinkedHashMap<BottomTabBean,BottomItemFragment> mItems = new LinkedHashMap<>(5);

    private int mCurrentFragment = 0;
    private int mIndexFragment = 0;
    private int mClickColor = 0;

    @BindView(R2.id.bottom_bar)
    LinearLayout mBottomBarContainer;


    @Override
    public Object setLayout() {
        return R.layout.main_bottom_tab_and_fragment_container;
    }

    protected abstract LinkedHashMap<BottomTabBean,BottomItemFragment> setItems(LinkedHashMap<BottomTabBean,BottomItemFragment> items);

    protected abstract int setIndexFragment();

    protected  abstract int setClickColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexFragment = setIndexFragment();
        if (setClickColor() != 0){
            mClickColor = setClickColor();
        }

        mItems = setItems(mItems);

        for (Map.Entry<BottomTabBean,BottomItemFragment> entry : mItems.entrySet()){
            BottomTabBean tabBean = entry.getKey();
            BottomItemFragment itemFragment = entry.getValue();
            mItemTabBeans.add(tabBean);
            mItemFragments.add(itemFragment);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        int itemSize = mItems.size();
        for (int i = 0; i < itemSize; i++){
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_icon_text_layout,mBottomBarContainer);
            RelativeLayout itemBar = (RelativeLayout) mBottomBarContainer.getChildAt(i);
            itemBar.setTag(i);
            itemBar.setOnClickListener(this);

            ImageView icon = (ImageView) itemBar.getChildAt(0);
            TextView title = (TextView) itemBar.getChildAt(1);
            BottomTabBean bottomTabBean = mItemTabBeans.get(i);

            icon.setBackgroundResource(bottomTabBean.getIcon());
            title.setText(bottomTabBean.getTitle());

            if (i == mIndexFragment){
                // TODO: 2019/1/24 设置被点击的图片状态
                title.setTextColor(mClickColor);
            }
        }

        ISupportFragment[] supportFragments = mItemFragments.toArray(new ISupportFragment[itemSize]);
        getSupportDelegate().loadMultipleRootFragment(R.id.main_fragment,mIndexFragment,supportFragments);
    }

    private void reset(){
        int count = mBottomBarContainer.getChildCount();
        for (int i = 0; i < count; i++){
            RelativeLayout item = (RelativeLayout) mBottomBarContainer.getChildAt(i);
            ImageView icon = (ImageView) item.getChildAt(0);
            icon.setBackgroundResource(mItemTabBeans.get(i).getIcon());
            TextView title = (TextView) item.getChildAt(1);
            title.setTextColor(Color.GRAY);
        }
    }


    @Override
    public void onClick(View v) {
        reset();
        RelativeLayout item = (RelativeLayout) v;
        ImageView icon = (ImageView) item.getChildAt(0);
        TextView title = (TextView) item.getChildAt(1);
        title.setTextColor(mClickColor);

        int tag = (int) v.getTag();
        getSupportDelegate().showHideFragment(mItemFragments.get(tag),mItemFragments.get(mCurrentFragment));
        mCurrentFragment = tag;
    }
}
