package com.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zjr on 2017/9/18.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String CREATE_USERINFO_TABLE = "CREATE TABLE userInfo(" +
            "username TEXT DEFAULT \"\"," +
            "password TEXT DEFAULT \"\"," +
            "level int DEFAULT \"\"," +
            "role int," +
            "emailaddress TEXT DEFAULT \"\")";

    private static final String CREATE_CLASSROOM_TABLE =  "create table classroom(" +
            "number integer," +
            "size integer," +
            "floor integer," +
            "state12 integer," +
            "state34 integer," +
            "state56 integer," +
            "state78 integer," +
            "state910 integer)";


    public MySQLiteHelper(Context context) {
        super(context, "DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERINFO_TABLE);
        db.execSQL(CREATE_CLASSROOM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
