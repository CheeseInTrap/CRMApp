package com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.crmapp.R;
import com.model.Constant;
import com.util.ToastUtil;
import com.util.db.MyDatabaseHelper;
import com.util.db.MySQLiteHelper;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by user on 2017/11/1.
 */

public class CRRecommendActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private MySQLiteHelper helper = new MySQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crrecommend);


       //MySQLiteHelper helper = new MySQLiteHelper(this);
        SQLiteDatabase writer = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Random random = new Random();
        for (int i = 100; i < 300; i = i + 10) {

            cv.put(Constant.KEY_NUM, i);
            cv.put(Constant.KEY_SIZE, random.nextInt(2));
            cv.put(Constant.KEY_FLOOR, i / 100);
            cv.put(Constant.KEY_STATE12, random.nextInt(2));
            cv.put(Constant.KEY_STATE34, random.nextInt(2));
            cv.put(Constant.KEY_STATE56, random.nextInt(2));
            cv.put(Constant.KEY_STATE78, random.nextInt(2));
            cv.put(Constant.KEY_STATE910, random.nextInt(2));


            writer.insert("classroom", null, cv);
            // cv.clear();
        }
        writer.close();


        Button ok = (Button) findViewById(R.id.OK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText FloorET = (EditText) findViewById(R.id.Floor_text);
                String Floor = FloorET.getText().toString();
                EditText SizeET = (EditText) findViewById(R.id.Size_text);
                String Size = SizeET.getText().toString();
                if(Floor.equals("1")==false && Floor.equals("2")==false){
                    Toast.makeText(getApplicationContext(),"楼层：1~2",Toast.LENGTH_SHORT).show();
                }else{
                    if(Size.equals("0")==false && Size.equals("1")==false){
                        Toast.makeText(getApplicationContext(),"规模：0~1",Toast.LENGTH_SHORT).show();
                    }else{
                        //SQLiteDatabase db = dbHelper.getWritableDatabase();

                        SQLiteDatabase writer = helper.getWritableDatabase();
                        Cursor cursor = writer.query("classroom", null, "floor = ? and size = ?", new String[]{Floor, Size}, null, null, null);
                        ArrayList<Integer> Snum = new ArrayList<Integer>();
                        ArrayList<Integer> Sstate12 = new ArrayList<Integer>();
                        ArrayList<Integer> Sstate34 = new ArrayList<Integer>();
                        ArrayList<Integer> Sstate56 = new ArrayList<Integer>();
                        ArrayList<Integer> Sstate78 = new ArrayList<Integer>();
                        ArrayList<Integer> Sstate910 = new ArrayList<Integer>();
                        if (cursor.moveToFirst()) {
                            do {
                                int number = cursor.getInt(cursor.getColumnIndex("number"));
                                int state12 = cursor.getInt(cursor.getColumnIndex("state12"));
                                int state34 = cursor.getInt(cursor.getColumnIndex("state34"));
                                int state56 = cursor.getInt(cursor.getColumnIndex("state56"));
                                int state78 = cursor.getInt(cursor.getColumnIndex("state78"));
                                int state910 = cursor.getInt(cursor.getColumnIndex("state910"));
                                Snum.add(number);
                                Sstate12.add(state12);
                                Sstate34.add(state34);
                                Sstate56.add(state56);
                                Sstate78.add(state78);
                                Sstate910.add(state910);
                            } while (cursor.moveToNext());
                        }
                        cursor.close();

                        Intent intent = new Intent(CRRecommendActivity.this, CRRecresultActivity.class);
                        intent.putExtra("Snum", Snum);
                        intent.putExtra("Sstate12", Sstate12);
                        intent.putExtra("Sstate34", Sstate34);
                        intent.putExtra("Sstate56", Sstate56);
                        intent.putExtra("Sstate78", Sstate78);
                        intent.putExtra("Sstate910", Sstate910);

                        startActivity(intent);
                    }
                }
            }
        });
    }

}
