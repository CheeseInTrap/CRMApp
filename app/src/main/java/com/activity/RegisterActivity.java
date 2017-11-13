package com.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.example.user.crmapp.R;
import com.model.Constant;
import com.model.User;
import com.util.ToastUtil;
import com.util.db.MySQLiteHelper;
import com.view.ActionBarView;

import java.util.List;
import java.util.Random;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    //从页面获取用户名和密码并存入数据库
    private EditText Name;
    private EditText EmailAddress;
    private EditText Password;
    private EditText PasswordConfirm;

    private ActionBarView actionBarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initActionBarView();
        //点击注册后从页面获取用户名和密码并存入数据库
        //跳回登录界面
        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = (EditText) findViewById(R.id.etName);
                EmailAddress = (EditText) findViewById(R.id.etEmailAddress);
                Password = (EditText) findViewById(R.id.etPassword);
                PasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);

                MySQLiteHelper helper = new MySQLiteHelper(RegisterActivity.this);


                if (!isEmail(EmailAddress.getText().toString())) {
                    ToastUtil.showToast(RegisterActivity.this, "邮箱格式不符合要求");
                } else {

                    BmobQuery<User> query = new BmobQuery<User>();
                    query.addWhereEqualTo("email", EmailAddress.getText().toString());
                    query.findObjects(new FindListener<User>() {
                        @Override
                        public void done(List<User> list, BmobException e) {
                            if (e == null) {
                                if (list.size() != 0) {
                                    ToastUtil.showToast(RegisterActivity.this, "该邮箱已被注册过");
                                }else{
                                    if (!Password.getText().toString().equals(PasswordConfirm.getText().toString())) {
                                        ToastUtil.showToast(RegisterActivity.this, "确认密码错误");
                                    } else {
                                        User user = new User(EmailAddress.getText().toString()
                                                ,Name.getText().toString()
                                                , Password.getText().toString()
                                                , new Random().nextInt(5) + 1
                                                , new Random().nextInt(1) + 1);
                                        user.save(new SaveListener<String>() {
                                            @Override
                                            public void done(String s, BmobException e) {
                                                if (e == null) {
                                                    ToastUtil.showToast(RegisterActivity.this, "注册成功");
                                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    ToastUtil.showToast(RegisterActivity.this, "注册失败");
                                                }
                                            }
                                        });
                                    }
                                }
                            } else {
                                ToastUtil.showToast(RegisterActivity.this, "出错了...");
                            }
                        }
                    });

                }


            }
        });

    }

    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
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
