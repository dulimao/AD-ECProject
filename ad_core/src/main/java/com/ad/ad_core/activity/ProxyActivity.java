package com.ad.ad_core.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.ContentFrameLayout;

import com.ad.ad_core.R;
import com.ad.ad_core.fragment.AD_Fragment;

import androidx.annotation.Nullable;
import me.yokeyword.fragmentation.SupportActivity;


/**
*@author 杜立茂
*@date 2019/1/19 20:04
*@description 单Activity框架
*/
public abstract class ProxyActivity extends SupportActivity {

    public abstract AD_Fragment setRootFrgment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        @SuppressLint("RestrictedApi") ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.contentfragment_id);
        setContentView(contentFrameLayout);
        if (setRootFrgment() == null){
            throw new NullPointerException("rootFragment is null");
        }
        if (savedInstanceState == null){
            loadRootFragment(R.id.contentfragment_id,setRootFrgment());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
