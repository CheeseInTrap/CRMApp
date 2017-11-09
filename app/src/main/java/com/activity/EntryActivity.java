package com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.example.user.crmapp.R;
import com.util.PreferenceUtil;
import com.util.db.MySQLiteHelper;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);


        String username = PreferenceUtil.getData(this,"loginInfo","username");
        if (username == null){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else {
            startActivity(new Intent(this,MainActivity.class));
        }


        MySQLiteHelper helper = new MySQLiteHelper(this,"userInfo",null,1);
        //helper.onCreate();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
