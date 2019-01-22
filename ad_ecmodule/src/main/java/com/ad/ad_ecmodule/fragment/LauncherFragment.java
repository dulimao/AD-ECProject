package com.ad.ad_ecmodule.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ad.ad_core.app.ILauncherListener;
import com.ad.ad_core.app.OnLauncherListenerTag;
import com.ad.ad_core.app.user_center.IUserChecker;
import com.ad.ad_core.app.user_center.UserAccountManager;
import com.ad.ad_core.fragment.AD_Fragment;
import com.ad.ad_core.fragment.BaseFragment;
import com.ad.ad_core.utils.timer.BaseTimerTask;
import com.ad.ad_core.utils.timer.ITimerListener;
import com.ad.ad_ecmodule.R;
import com.ad.ad_ecmodule.R2;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherFragment extends AD_Fragment implements ITimerListener {

    @BindView(R2.id.tv_timer)
    TextView mTvTimer;

    @OnClick(R2.id.tv_timer)
    void onTimerViewClick(){
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
            checkFirstInstall();
        }
    }

    private Timer mTimer = null;
    private int count = 3;

    private ILauncherListener mILauncherListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer(){
        mTimer = new Timer();
        BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask,0,1000);
    }

    private void checkFirstInstall(){
        SharedPreferences sp = getProxyActivity().getSharedPreferences("first_install", Context.MODE_PRIVATE);
        boolean not_first_install = sp.getBoolean("not_first_install",false);
        if (not_first_install){
            UserAccountManager.checkUserState(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherListenerTag.Signed);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherListenerTag.UnSigned);
                    }
                }
            });
        }else {
            start(new LauncherScrollFragment(),SINGLETASK);
        }
    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTimer.setText("跳过" + count);
                count--;
                if (count < 0){
                    if (mTimer != null){
                        mTimer.cancel();
                        mTimer = null;
                        checkFirstInstall();
                    }
                }
            }
        });
    }
}
