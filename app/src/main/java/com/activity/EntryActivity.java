package com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.SDKInitializer;
import com.example.user.crmapp.R;
import com.util.PreferenceUtil;

import cn.bmob.v3.Bmob;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        SDKInitializer.initialize(getApplicationContext());
        Bmob.initialize(this, "0e4980520fdf0adcce95d4a33a27594f");


        String email = PreferenceUtil.getData(this,"userInfo","email");
        if (email == null){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);


        }else {
            startActivity(new Intent(this,MainActivity.class));
        }

        finish();

    }
}
