package com.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.Constant;
import com.util.db.MySQLiteHelper;

public class CheckReserveActivity extends AppCompatActivity {

    private TextView tvNum;
    private TextView tvTime;
    private TextView tvDate;
    private TextView tvReason;
    private Button btnAgree;
    private Button btnReject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_check);

        tvNum = (TextView) findViewById(R.id.tv_num);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvReason = (TextView) findViewById(R.id.tv_reason);

        btnAgree = (Button) findViewById(R.id.btn_agree);
        btnReject = (Button) findViewById(R.id.btn_reject);

        Bundle bundle = getIntent().getExtras();

        tvNum.setText(""+bundle.getInt("number"));
        String time = "";
        switch (bundle.getInt("time")){
            case 0:
                time = time + "1-2节";
                break;
            case 1:
                time = time + "3-4节";
                break;
            case 2:
                time = time + "5-6节";
                break;
            case 3:
                time = time + "7-8节";
                break;
            case 4:
                time = time + "9-10节";
                break;
            default:
                break;

        }
        tvTime.setText(time);
        tvDate.setText(bundle.getInt("year")+"-"+(bundle.getInt("month")+1)+"-"+bundle.getInt("date"));
        tvReason.setText(bundle.getString("reason"));
        String state="";
        switch (bundle.getInt("state")){
            case Constant.STATE_PASS:
                state = state+"通过";
                break;
            case Constant.STATE_UNCHECKED:
                state = state+"待审核";
                break;
            case Constant.STATE_REJECTED:
                state = state+"未通过";
                break;
            default:
                break;
        }
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQLiteHelper helper = new MySQLiteHelper(CheckReserveActivity.this);
                SQLiteDatabase writer = helper.getWritableDatabase();

                int itemId = getIntent().getExtras().getInt("id");
                ContentValues cv = new ContentValues();
                cv.put("state",Constant.STATE_PASS);
                writer.update("reserveInfo",cv,"_id=?",new String[]{""+itemId});
                writer.close();

                new AlertDialog.Builder(CheckReserveActivity.this)
                        .setTitle("提醒")
                        .setMessage("操作成功")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .show();
            }
        });
        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQLiteHelper helper = new MySQLiteHelper(CheckReserveActivity.this);
                SQLiteDatabase writer = helper.getWritableDatabase();

                int itemId = getIntent().getExtras().getInt("id");
                ContentValues cv = new ContentValues();
                cv.put("state",Constant.STATE_REJECTED);
                writer.update("reserveInfo",cv,"_id=?",new String[]{""+itemId});
                writer.close();

                new AlertDialog.Builder(CheckReserveActivity.this)
                        .setTitle("提醒")
                        .setMessage("操作成功")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .show();
            }
        });

    }
}
