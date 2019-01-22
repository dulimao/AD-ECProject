package com.ad.ad_core.app.user_center;

import com.ad.ad_core.utils.ADPreference;

public class UserAccountManager {

    enum SignInTag{
        SIGN_TAG
    }

    //设置用户登录状态
    public static void setSignInState(boolean state){
        ADPreference.setAppFlag(SignInTag.SIGN_TAG.name(),state);
    }

    public static boolean isSignIn(){
        return ADPreference.getAppFlag(SignInTag.SIGN_TAG.name());
    }

    public static void checkUserState(IUserChecker userChecker){
        if (isSignIn()){
            userChecker.onSignIn();
        }else {
            userChecker.onNotSignIn();
        }
    }


}
