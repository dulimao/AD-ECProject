package com.ad.ad_ecmodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ad.ad_core.fragment.main.BottomItemFragment;
import com.ad.ad_core.net.ApiClient;
import com.ad.ad_core.net.callback.ISuccess;
import com.ad.ad_core.ui.RefreshHandler;
import com.ad.ad_core.ui.recycler.MultipleFields;
import com.ad.ad_core.ui.recycler.MultipleItemEntity;
import com.ad.ad_ecmodule.R;
import com.ad.ad_ecmodule.R2;
import com.ad.ad_ecmodule.data_converter.IndexDataConverter;

import java.util.ArrayList;

import butterknife.BindView;

public class IndexFragment extends BottomItemFragment {


    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R2.id.recycler_index)
    RecyclerView mRecyclerView;
    @BindView(R2.id.tb_index)
    Toolbar mToolBar;
    @BindView(R2.id.tv_scan)
    TextView mTvScan;
    @BindView(R2.id.edt_search)
    EditText mEdtSearch;
    @BindView(R2.id.tv_search)
    TextView mTvSearch;

    private RefreshHandler mRefreshHandler;


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_orange_light)
                ,getResources().getColor(android.R.color.holo_blue_light)
                ,getResources().getColor(android.R.color.holo_purple));
        mRefreshLayout.setProgressViewOffset(true,100,200);

    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = new RefreshHandler(mRefreshLayout);
        ApiClient.newBuilder().url("http://baidu.com/index")
                .formBody("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(response);
                        ArrayList<MultipleItemEntity> entities = converter.convert();
                        Log.d("IndexFragement",entities.get(0).getField(MultipleFields.image).toString());
                    }
                }).build().get();

    }
}
