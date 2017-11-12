package com.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.crmapp.R;

//登录页面Aty

import com.util.PreferenceUtil;
import com.util.ToastUtil;
import com.util.db.MySQLiteHelper;


public class LoginActivity extends AppCompatActivity {


    private EditText EmailAddress;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySQLiteHelper helper =new MySQLiteHelper(LoginActivity.this);
                EmailAddress=(EditText) findViewById(R.id.etEmailAddress);
                Password=(EditText)findViewById(R.id.etPassword);

                String email = EmailAddress.getText().toString();
                SQLiteDatabase dbuserRead=helper.getReadableDatabase();
                Cursor c=dbuserRead.query("userInfo",null,"emailaddress=?",new String[]{email},null,null,null);
                if (c.moveToNext())
                {

                    String dbpassword=c.getString(c.getColumnIndex("password"));
                    String username = c.getString(c.getColumnIndex("username"));
                    int level = c.getInt(c.getColumnIndex("level"));
                    int role = c.getInt(c.getColumnIndex("role"));
                    if (Password.getText().toString().equals(dbpassword))
                    {
                        Intent intenttest=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intenttest);
                        ToastUtil.showToast(LoginActivity.this,"登录成功");
                        PreferenceUtil.setData(LoginActivity.this,"userInfo","username",username);
                        PreferenceUtil.setData(LoginActivity.this,"userInfo","email",email);
                        PreferenceUtil.setInt(LoginActivity.this,"userInfo","level",level);
                        PreferenceUtil.setInt(LoginActivity.this,"userInfo","role",role);
                    }else{
                        ToastUtil.showToast(LoginActivity.this,"密码错误");
                    }
                }else{
                    ToastUtil.showToast(LoginActivity.this,"您尚未注册");
                }
                c.close();
                dbuserRead.close();
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
