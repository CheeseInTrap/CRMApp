package com.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.user.crmapp.R;
import com.util.ToastUtil;
import com.util.db.MySQLiteHelper;

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

                MySQLiteHelper helper =new MySQLiteHelper(RegisterActivity.this);

                //判断邮箱是否存在
                SQLiteDatabase dbuserRead=helper.getReadableDatabase();
                Cursor cursor=dbuserRead.query("userInfo",null,"emailaddress=?",new String[]{EmailAddress.getText().toString()},null,null,null);
                if (cursor.moveToNext())
                {
                    ToastUtil.showToast(RegisterActivity.this,"该邮箱已被注册过");
                }
                else
                {
                    if (!Password.getText().toString().equals(PasswordConfirm.getText().toString()))
                    {
                        ToastUtil.showToast(RegisterActivity.this,"确认密码错误");
                    }
                    else
                    {
                        SQLiteDatabase dbuserWrite=helper.getWritableDatabase();
                        ContentValues cv=new ContentValues();
                        cv.put("username",Name.getText().toString());
                        cv.put("password",Password.getText().toString());
                        cv.put("emailaddress",EmailAddress.getText().toString());
                        cv.put("level",1);

                        dbuserWrite.insert("userInfo",null,cv);
                        dbuserWrite.close();


                        SQLiteDatabase reader = helper.getReadableDatabase();
                        Cursor c= reader.query("userInfo",null,"emailaddress=?",new String[]{EmailAddress.getText().toString()},null,null,null);

                        if (c.moveToNext()){
                            ToastUtil.showToast(RegisterActivity.this,"注册成功");
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);

                        }else{
                            ToastUtil.showToast(RegisterActivity.this,"注册失败");
                        }
                        reader.close();
                    }
                }


                dbuserRead.close();

            }
        });
    }
}
