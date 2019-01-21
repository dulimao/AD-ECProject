package com.ad.ad_ecmodule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.ad.ad_core.fragment.AD_Fragment;
import com.ad.ad_ecmodule.R;
import com.ad.ad_ecmodule.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpFragment extends AD_Fragment {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword;

    @OnClick(R2.id.btn_sign_up)
    void onSignUp(){

        if (checkForm()){

        }else {

        }

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

    @Override
    public Object setLayout() {
        return R.layout.fragment_signup;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
