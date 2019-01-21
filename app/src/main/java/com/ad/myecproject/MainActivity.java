package com.ad.myecproject;


import com.ad.ad_core.activity.ProxyActivity;
import com.ad.ad_core.fragment.AD_Fragment;
import com.ad.ad_ecmodule.fragment.LauncherFragment;
import com.ad.ad_ecmodule.fragment.LauncherScrollFragment;

public class MainActivity extends ProxyActivity {


    @Override
    public AD_Fragment setRootFrgment() {
        return new LauncherFragment();
    }
}
