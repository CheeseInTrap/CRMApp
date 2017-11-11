package com.util.db;

/**
 * Created by asus on 2017/11/11.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by asus on 2017/11/11.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public  static final String CREATE_CLASSROOMZHENGXIN = "create table ClassRoomZX ("
            +"id integer primary key autoincrement,"
            +"number integer,"
            +"size integer,"
            +"floor integer,"
            +"state12 integer,"
            +"state34 integer,"
            +"state56 integer,"
            +"state78 integer,"
            +"state910 integer)";

    public  static final String CREATE_CLASSROOMCHENGYI = "create table ClassRoomCY ("
            +"id integer primary key autoincrement,"
            +"number integer,"
            +"size integer,"
            +"floor integer,"
            +"state12 integer,"
            +"state34 integer,"
            +"state56 integer,"
            +"state78 integer,"
            +"state910 integer)";

    public  static final String CREATE_CLASSROOMZHIZHI = "create table ClassRoomZZ ("
            +"id integer primary key autoincrement,"
            +"number integer,"
            +"size integer,"
            +"floor integer,"
            +"state12 integer,"
            +"state34 integer,"
            +"state56 integer,"
            +"state78 integer,"
            +"state910 integer)";


    private Context mContext;

    public  MyDatabaseHelper(Context context, String name,
                             SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public  void  onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_CLASSROOMZHENGXIN);
        db.execSQL(CREATE_CLASSROOMCHENGYI);
        db.execSQL(CREATE_CLASSROOMZHIZHI);

        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists ClassRoomZX");
        db.execSQL("drop table if exists ClassRoomCY");
        db.execSQL("drop table if exists ClassRoomZZ");
        onCreate(db);
    }
}
