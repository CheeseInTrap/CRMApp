package com.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.example.user.crmapp.R;

//登录页面Aty

import com.util.db.MySQLiteHelper;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView tvRegister=(TextView)findViewById(R.id.tvStartRegister);
        String registerquery="没有账号？点击注册";
        SpannableString spStr = new SpannableString(registerquery);
        ClickableSpan clickSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intentRegister=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intentRegister);
            }
        };

        spStr.setSpan(clickSpan, 0, registerquery.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvRegister.setText(spStr);
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance());//设置超链接为可点击状态
        MySQLiteHelper helper = new MySQLiteHelper(this,"userInfo",null,1);


    }
}
