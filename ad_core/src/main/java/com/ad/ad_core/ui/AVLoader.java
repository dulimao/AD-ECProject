package com.ad.ad_core.ui;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ad.ad_core.R;
import com.ad.ad_core.utils.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
*@author 杜立茂
*@date 2019/1/20 19:54
*@description 对话框
*/
public class AVLoader {

    private static final int LOADER_SIZE_RATIO = 8;
    private static final LoaderStyle DEFAULT_LOADER = LoaderStyle.BallClipRotateIndicator;
    private static final ArrayList<AppCompatDialog> dialogs = new ArrayList<>();
    private static final Handler HANDLER = new Handler();


    public static void showLoader(Context context,LoaderStyle style){
        AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        AVLoadingIndicatorView loader = LoaderCreator.create(style.name(),context);
        int width = DimenUtil.getScreenWidth();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width / LOADER_SIZE_RATIO,width / LOADER_SIZE_RATIO);
        params.gravity = Gravity.CENTER;
        dialog.addContentView(loader,params);
        dialogs.add(dialog);
        dialog.show();
    }

    public static void showLoader(Context context){
        showLoader(context,DEFAULT_LOADER);
    }

    public static void stopLoader(){
        for (int i = 0; i < dialogs.size(); i++){
            final AppCompatDialog dialog = dialogs.get(i);
            if (dialog != null){
                HANDLER.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.cancel();
                    }
                },1000);
            }
        }
        dialogs.clear();
    }



}
