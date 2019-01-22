package com.ad.myecproject;


import android.os.Bundle;
import android.widget.Toast;

import com.ad.ad_core.activity.ProxyActivity;
import com.ad.ad_core.app.Configrator;
import com.ad.ad_core.app.ILauncherListener;
import com.ad.ad_core.app.OnLauncherListenerTag;
import com.ad.ad_core.fragment.AD_Fragment;
import com.ad.ad_ecmodule.callback.ISignListener;
import com.ad.ad_ecmodule.fragment.LauncherFragment;
import com.ad.ad_ecmodule.fragment.LauncherScrollFragment;
import com.ad.ad_ecmodule.fragment.SignInFragment;

import androidx.annotation.Nullable;


// TODO: 2019/1/22 GreenDao，老师的反射课程
public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configrator.getInstance().configActivityContext(this);
    }

    @Override
    public AD_Fragment setRootFrgment() {
        return new LauncherFragment();
    }



    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        startWithPop(new MainFragment());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLauncherFinish(OnLauncherListenerTag tag) {
        switch (tag) {
            case Signed:
                //登录了
                startWithPop(new MainFragment());
                break;
            case UnSigned:
                //未登录
                startWithPop(new SignInFragment());
                break;
            default:
                break;
        }
    }
}
