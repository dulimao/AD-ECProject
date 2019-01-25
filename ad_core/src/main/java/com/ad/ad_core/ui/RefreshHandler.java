package com.ad.ad_core.ui;

import android.support.v4.widget.SwipeRefreshLayout;

import com.ad.ad_core.app.ConfigLoader;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mSwiperRefresh;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout){
        this.mSwiperRefresh = swipeRefreshLayout;
        this.mSwiperRefresh.setOnRefreshListener(this);
    }

    private void refresh(){
        mSwiperRefresh.setRefreshing(true);
        ConfigLoader.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwiperRefresh.setRefreshing(false);
            }
        },3000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
