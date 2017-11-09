package com.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zjr on 2017/9/18.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String CREATE_USERINFO_TABLE = "create table userInfo(" +
            "username text," +
            "password text," +
            "email text)";

    /*private static final String CREATE_POINTS_TABLE = "create table points(" +
            "network_id text," +
            "point_id text primary key,"+
            "version text,"+
            "longitude text," +
            "latitude text)";*/
    private static final String CREATE_SIDES_TABLE = "create table sides(" +
            "network_id text," +
            "side_id text,"+
            "point_id text"+
            "point_num text"+
            "longitude text," +
            "latitude text)";

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERINFO_TABLE);
        //db.execSQL(CREATE_POINTS_TABLE);
        //db.execSQL(CREATE_SIDES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
