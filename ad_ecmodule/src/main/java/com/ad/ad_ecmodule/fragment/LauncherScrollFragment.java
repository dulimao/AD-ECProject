package com.ad.ad_ecmodule.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.ad.ad_core.app.ILauncherListener;
import com.ad.ad_core.app.OnLauncherListenerTag;
import com.ad.ad_core.app.user_center.IUserChecker;
import com.ad.ad_core.app.user_center.UserAccountManager;
import com.ad.ad_core.fragment.AD_Fragment;
import com.ad.ad_ecmodule.R;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;

/**
*@author 杜立茂
*@date 2019/1/21 16:29
*@description 引导页
*/
public class LauncherScrollFragment extends AD_Fragment implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner;
    private ArrayList<Integer> mImages = new ArrayList<>();

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
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    private void initBanner() {
        mImages.add(R.mipmap.launcher_01);
        mImages.add(R.mipmap.launcher_02);
        mImages.add(R.mipmap.launcher_03);
        mConvenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_banner;
            }
        },mImages)
        .setCanLoop(false)
        .setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        if (position == mImages.size() - 1){
            SharedPreferences sp = getProxyActivity().getSharedPreferences("first_install",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("not_first_install",true);
            editor.apply();
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
        }
    }


    public class LocalImageHolderView extends Holder<Integer> {
        private ImageView imageView;

        public LocalImageHolderView(View itemView) {
            super(itemView);

        }

        @Override
        protected void initView(View itemView) {
            imageView = itemView.findViewById(R.id.img_banner);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        @Override
        public void updateUI(Integer data) {
            imageView.setBackgroundResource(data);
        }
    }
}
