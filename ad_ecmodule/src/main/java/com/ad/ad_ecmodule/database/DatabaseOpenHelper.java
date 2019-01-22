package com.ad.ad_ecmodule.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

public class DatabaseOpenHelper extends DaoMaster.OpenHelper {

    public DatabaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
