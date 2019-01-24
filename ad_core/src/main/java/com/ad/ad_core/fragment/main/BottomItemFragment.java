package com.ad.ad_core.fragment.main;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ad.ad_core.R;
import com.ad.ad_core.fragment.AD_Fragment;

/**
*@author 杜立茂
*@date 2019/1/23 9:15
*@description 底部导航键 BottomTabBean相对应的fragment基类
*/
public abstract class BottomItemFragment extends AD_Fragment implements View.OnKeyListener {

    private long mExitTime;
    private static final long EXIT_TIME = 2000;


    @Override
    public void onResume() {
        super.onResume();
        //重新获取焦点
        View view = getView();
        if (view != null){
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis() - mExitTime > EXIT_TIME){
                Toast.makeText(getContext(),"再点一次退出" + getString(R.string.app_name),Toast.LENGTH_LONG).show();
                mExitTime = System.currentTimeMillis();
            }else {
                _mActivity.finish();
                if (mExitTime != 0){
                    mExitTime = 0;
                }
            }
        }
        return true;
    }
}
