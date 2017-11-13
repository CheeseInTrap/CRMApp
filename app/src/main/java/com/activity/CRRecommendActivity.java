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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;

import com.example.user.crmapp.R;
import com.model.Constant;
import com.util.ToastUtil;
import com.util.db.MyDatabaseHelper;
import com.util.db.MySQLiteHelper;
import com.view.ActionBarView;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by user on 2017/11/1.
 */

public class CRRecommendActivity extends AppCompatActivity {

    private EditText etFloor;
    private RadioButton rbSmall;
    private RadioButton rbMedium;
    private RadioButton rbLarge;

    private ActionBarView actionBarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crrecommend);
        initActionBarView();


        etFloor = (EditText) findViewById(R.id.et_floor);
        rbSmall = (RadioButton) findViewById(R.id.rb_small);
        rbMedium = (RadioButton) findViewById(R.id.rb_medium);
        rbLarge = (RadioButton) findViewById(R.id.rb_large);



        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int floor =0;
                if (etFloor.getText().toString().equals("")){
                    floor =0;
                }else{
                    floor = Integer.parseInt(etFloor.getText().toString());
                }

                Intent intent = new Intent(CRRecommendActivity.this,CRRecresultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("floor",floor);
                if (rbSmall.isChecked()){
                    bundle.putInt("size",Constant.SIZE_SMALL);
                }else if (rbMedium.isChecked()){
                    bundle.putInt("size",Constant.SIZE_MEDIUM);
                }else if (rbLarge.isChecked()){
                    bundle.putInt("size",Constant.SIZE_LARGE);
                }else{

                    bundle.putInt("size",-1);
                }
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });



    }

    public void initActionBarView(){
        actionBarView = (ActionBarView) findViewById(R.id.action_bar);
        actionBarView.setOnBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
