package com.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.util.db.DBUser;

public class RegisterActivity extends AppCompatActivity {

    //从页面获取用户名和密码并存入数据库
    private EditText Name;
    private EditText EmailAddress;
    private EditText Password;
    private EditText PasswordConfirm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //点击注册后从页面获取用户名和密码并存入数据库
        //跳回登录界面
        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name=(EditText) findViewById(R.id.etName);
                EmailAddress=(EditText)findViewById(R.id.etEmailAddress);
                Password=(EditText)findViewById(R.id.etPassword);
                PasswordConfirm=(EditText)findViewById(R.id.etPasswordConfirm);

                DBUser dbuser=new DBUser(RegisterActivity.this);
                SQLiteDatabase dbuserWrite=dbuser.getWritableDatabase();
                ContentValues cv=new ContentValues();
                cv.put("username",Name.getText().toString());
                cv.put("password",Password.getText().toString());
                cv.put("emailaddress",EmailAddress.getText().toString());
                cv.put("level",1);

                dbuserWrite.insert("userInfo",null,cv);
                dbuserWrite.close();

                DBUser dbUser1=new DBUser(RegisterActivity.this);
                SQLiteDatabase dbuser1Read=dbUser1.getReadableDatabase();
                Cursor c=dbuser1Read.query("userInfo",null,null,null,null,null,null);
                while (c.moveToNext())
                {
                    System.out.println("传入数据库成功");
                    System.out.println(c.getString(c.getColumnIndex("username")));
                    System.out.println(c.getString(c.getColumnIndex("password")));
                    System.out.println(c.getString(c.getColumnIndex("emailaddress")));
                }
                c.close();

                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}
