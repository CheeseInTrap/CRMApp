package com.activity;

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

import com.example.user.crmapp.R;
import com.model.Constant;
import com.util.ToastUtil;
import com.util.db.MyDatabaseHelper;
import com.util.db.MySQLiteHelper;

import java.util.Random;


/**
 * Created by user on 2017/11/1.
 */

public class CRRecommendActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crrecommend);


        MySQLiteHelper helper = new MySQLiteHelper(this);
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
                EditText BuildingET = (EditText) findViewById(R.id.Building_text);
                String Building = BuildingET.getText().toString();
                EditText FloorET = (EditText) findViewById(R.id.Floor_text);
                String Floor = FloorET.getText().toString();
                EditText SizeET = (EditText) findViewById(R.id.Size_text);
                String Size = SizeET.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("classroom", null, "floor = ? and size = ?", new String[]{Floor, Size}, null, null, null);
                String result = "";
                if (cursor.moveToFirst()) {
                    do {
                        int number = cursor.getInt(cursor.getColumnIndex("number"));
                        result += number;
                        result += " ";
                    } while (cursor.moveToNext());
                }
                TextView showData = (TextView) findViewById(R.id.show_data);
                showData.setText(result);
                cursor.close();
            }
        });
    }

}
