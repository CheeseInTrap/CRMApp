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
import com.model.ReserveInfo;
import com.util.ToastUtil;
import com.util.db.MySQLiteHelper;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class CheckReserveActivity extends AppCompatActivity {

    private TextView tvNum;
    private TextView tvTime;
    private TextView tvDate;
    private TextView tvReason;
    private Button btnAgree;
    private Button btnReject;

    private static ReserveInfo info;


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

        String id = bundle.getString("id");
        BmobQuery<ReserveInfo> query = new BmobQuery<>();
        query.getObject(id, new QueryListener<ReserveInfo>() {
            @Override
            public void done(ReserveInfo reserveInfo, BmobException e) {
                if (e == null){
                    info = reserveInfo;
                    tvNum.setText(""+info.getNumber());
                    String time = "";
                    switch (info.getTime()){
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
                    tvDate.setText(info.getYear()+"-"+(info.getMonth()+1)+"-"+info.getDate());
                    tvReason.setText(info.getReason());
                    String state="";
                    switch (info.getState()){
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

                }else {
                    ToastUtil.showToast(CheckReserveActivity.this,"出错了...");
                }
            }
        });



        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                info.setState(Constant.STATE_PASS);
                info.update(info.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null){
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
                        }else {
                            ToastUtil.showToast(CheckReserveActivity.this,"出错了...");
                        }
                    }
                });


            }
        });
        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setState(Constant.STATE_REJECTED);
                info.update(info.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null){
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
                        }else {
                            ToastUtil.showToast(CheckReserveActivity.this,"出错了...");
                        }
                    }
                });


            }
        });

    }
}
