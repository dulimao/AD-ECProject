package com.ad.ad_ecmodule.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ad.ad_core.app.user_center.UserAccountManager;
import com.ad.ad_core.fragment.AD_Fragment;
import com.ad.ad_core.net.ApiClient;
import com.ad.ad_core.net.callback.IFailure;
import com.ad.ad_core.net.callback.ISuccess;
import com.ad.ad_ecmodule.R;
import com.ad.ad_ecmodule.R2;
import com.ad.ad_ecmodule.callback.ISignListener;
import com.ad.ad_ecmodule.database.DatabaseManager;
import com.ad.ad_ecmodule.database.UserProfile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInFragment extends AD_Fragment {

    @BindView(R2.id.edit_sign_in_name)
    TextInputEditText mName;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_signin;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R2.id.btn_sign_in)
    void onSignUp(){

        if (checkForm()){
            ApiClient.newBuilder()
                    .url("http://baidu.com/user/signup")
                    .formBody("name", "adu")
                    .formBody("password", "123456")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Log.d("SignUpFragment","用户信息：" + response);

                            JSONObject jsonObject = JSON.parseObject(response).getJSONObject("data");
                            long userId = jsonObject.getLong("userId");
                            String name = jsonObject.getString("name");
                            String avatar = jsonObject.getString("avatar");
                            String gender = jsonObject.getString("gender");
                            String address = jsonObject.getString("address");
                            UserProfile userProfile = new UserProfile(4,name,avatar,gender,address);
                            //todo 不能重复写入，不然会报错
                            long result = DatabaseManager.getInstance().getUserProfileDao().insert(userProfile);

                            Log.d("SignUpFragment","写入数据库：" + result);

                            if (result != 0){
                                //登录成功，保存用户登录状态
                                UserAccountManager.setSignInState(true);
                                if (mISignListener != null){
                                    mISignListener.onSignInSuccess();
                                }
                            }

                        }
                    }).failure(new IFailure() {
                @Override
                public void onFailure(String msg) {
                    Log.d("SignUpFragment","注册失败：" + msg);
                    Toast.makeText(getProxyActivity(),"注册失败",Toast.LENGTH_LONG).show();

                }
            }).build().postFormBody();
        }else {

        }

    }

    @OnClick(R2.id.tv_link_sign_up)
    void onSinInLink(){
        start(new SignUpFragment());
    }

    private boolean checkForm(){
        String name = mName.getText().toString();
        String password = mPassword.getText().toString();

        boolean pass = true;

        if (name.equals("")){
            pass = false;
            mName.setError("请输入姓名");
        }else {
            mName.setError(null);
        }

        if (password.equals("") || password.length() < 6){
            pass = false;
            mPassword.setError("请输入至少六位字符的密码");
        }else {
            mPassword.setError(null);
        }

        return pass;
    }


}
