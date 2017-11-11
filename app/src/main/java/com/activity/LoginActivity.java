package com.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.crmapp.R;

//登录页面Aty

import com.util.ToastUtil;
import com.util.db.DBUser;
import com.util.db.MySQLiteHelper;


public class LoginActivity extends AppCompatActivity {


    private EditText EmailAddress;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        System.out.print("？？  成功");

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBUser dbuser=new DBUser(LoginActivity.this);
                EmailAddress=(EditText) findViewById(R.id.etEmailAddress);
                System.out.print(EmailAddress.getText().toString());
                Password=(EditText)findViewById(R.id.etPassword);
                SQLiteDatabase dbuserRead=dbuser.getReadableDatabase();
                Cursor c=dbuserRead.query("user",null,"emailaddress=?",new String[]{EmailAddress.getText().toString()},null,null,null);
                while (c.moveToNext())
                {
                    System.out.print("查找数据成功");
                    String dbpassword=c.getString(c.getColumnIndex("password"));
                    ToastUtil.showToast(LoginActivity.this,dbpassword);
                    if (Password.getText().toString().equals(dbpassword))
                    {
                        Intent intenttest=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intenttest);
                    }
                }
            }
        });



        //注册
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


    }
}
