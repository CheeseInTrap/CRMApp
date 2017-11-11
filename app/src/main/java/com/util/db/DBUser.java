package com.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zjr on 2017/9/18.
 */

public class DBUser extends SQLiteOpenHelper {
    private static final String CREATE_USERINFO_TABLE = "CREATE TABLE userInfo(" +
            "username TEXT DEFAULT \"\"," +
            "password TEXT DEFAULT \"\"," +
            "level int DEFAULT \"\"," +
            "emailaddress TEXT DEFAULT \"\")";



    public DBUser(Context context) {
        super(context, "UserInfo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERINFO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}