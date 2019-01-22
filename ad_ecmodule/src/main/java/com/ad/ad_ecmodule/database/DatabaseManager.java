package com.ad.ad_ecmodule.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DatabaseManager {

    private DaoSession mDaoSession;
    private UserProfileDao mUserProfileDao;

    private static final class DatabaseManagerHolder{
        private static final DatabaseManager instance = new DatabaseManager();
    }

    private DatabaseManager(){

    }

    public static DatabaseManager getInstance(){
        return DatabaseManagerHolder.instance;
    }

    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private void initDao(Context context){
        DatabaseOpenHelper helper = new DatabaseOpenHelper(context,"ec.db");
        Database database =  helper.getWritableDb();
        mDaoSession = new DaoMaster(database).newSession();
        mUserProfileDao = mDaoSession.getUserProfileDao();
    }

    public UserProfileDao getUserProfileDao(){
        return mUserProfileDao;
    }

}
