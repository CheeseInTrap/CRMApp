package com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.user.crmapp.R;
import com.util.db.MyDatabaseHelper;

import java.util.ArrayList;

/**
 * Created by asus on 2017/11/12.
 */

public class CRRecresultActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crquery);
        Intent intent = getIntent();


        //获取数据
        ArrayList<String> Snum = intent.getStringArrayListExtra("Snum");
        ArrayList<String> Sstate12 = intent.getStringArrayListExtra("Sstate12");
        ArrayList<String> Sstate34 = intent.getStringArrayListExtra("Sstate34");
        ArrayList<String> Sstate56 = intent.getStringArrayListExtra("Sstate56");
        ArrayList<String> Sstate78 = intent.getStringArrayListExtra("Sstate78");
        ArrayList<String> Sstate910 = intent.getStringArrayListExtra("Sstate910");


        //设置数据

    }
}
