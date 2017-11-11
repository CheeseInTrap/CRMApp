package com.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.example.user.crmapp.R;
import com.model.Constant;
import com.util.PreferenceUtil;
import com.util.db.MySQLiteHelper;

import java.util.Random;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        SDKInitializer.initialize(getApplicationContext());

        String email = PreferenceUtil.getData(this,"userInfo","email");
        if (email == null){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

            //先加上一堆教室的信息
            MySQLiteHelper helper = new MySQLiteHelper(this);
            SQLiteDatabase writer = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            Random random = new Random();
            for (int i=100;i<300;i=i+10){

                cv.put(Constant.KEY_NUM,i);
                cv.put(Constant.KEY_SIZE, random.nextInt(2));
                cv.put(Constant.KEY_FLOOR,i/100);
                cv.put(Constant.KEY_STATE12,random.nextInt(2));
                cv.put(Constant.KEY_STATE34,random.nextInt(2));
                cv.put(Constant.KEY_STATE56,random.nextInt(2));
                cv.put(Constant.KEY_STATE78,random.nextInt(2));
                cv.put(Constant.KEY_STATE910,random.nextInt(2));


                writer.insert("classroom",null,cv);
               // cv.clear();
            }
            writer.close();

            SQLiteDatabase reader = helper.getReadableDatabase();
            Cursor c = reader.query("classroom",null,null,null,null,null,null);
            while (c.moveToNext()){
                Log.v("数据库教室",""+c.getInt(c.getColumnIndex(Constant.KEY_NUM)));
            }




        }else {
            startActivity(new Intent(this,MainActivity.class));
        }

        finish();

    }
}
