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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.crmapp.R;

//登录页面Aty

import com.model.User;
import com.util.PreferenceUtil;
import com.util.ToastUtil;
import com.util.db.MySQLiteHelper;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


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

                BmobQuery<User> query = new BmobQuery<User>();
                query.addWhereEqualTo("email",EmailAddress.getText().toString());
                query.findObjects(new FindListener<User>() {
                    @Override
                    public void done(List<User> list, BmobException e) {
                        if (e == null){
                            if (list.size() == 0){
                                ToastUtil.showToast(LoginActivity.this,"您尚未注册");
                            }else{
                                User user = list.get(0);
                                if (Password.getText().toString().equals(user.getPassword())){

                                    PreferenceUtil.setData(LoginActivity.this,"userInfo","username",user.getUserName());
                                    PreferenceUtil.setData(LoginActivity.this,"userInfo","email",user.getEmail());
                                    PreferenceUtil.setInt(LoginActivity.this,"userInfo","level",user.getLevel());
                                    PreferenceUtil.setInt(LoginActivity.this,"userInfo","role",user.getRole());
                                    Intent intenttest=new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intenttest);
                                    ToastUtil.showToast(LoginActivity.this,"登录成功");
                                    finish();
                                }else {
                                    ToastUtil.showToast(LoginActivity.this,"密码错误");
                                    Password.setText("");
                                }
                            }
                        }else {
                            ToastUtil.showToast(LoginActivity.this,"出错了...");
                        }
                    }
                });

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
