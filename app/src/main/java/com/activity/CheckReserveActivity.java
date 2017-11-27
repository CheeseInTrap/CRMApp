package com.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.ClassRoom;
import com.model.Constant;
import com.model.ReserveInfo;
import com.util.ToastUtil;
import com.util.db.MySQLiteHelper;
import com.view.ActionBarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class CheckReserveActivity extends AppCompatActivity {

    private TextView tvNum;
    private TextView tvBuild;
    private TextView tvTime;
    private TextView tvDate;
    private TextView tvEmail;
    private TextView tvReason;
    private Button btnAgree;
    private Button btnReject;

    private TextView tv;

    private ActionBarView actionBarView;


    private static ReserveInfo info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_check);
        initActionBarView();

        tvNum = (TextView) findViewById(R.id.tv_num);
        tvBuild = (TextView) findViewById(R.id.tv_build);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvReason = (TextView) findViewById(R.id.tv_reason);

        btnAgree = (Button) findViewById(R.id.btn_agree);
        btnReject = (Button) findViewById(R.id.btn_reject);

        tv = (TextView) findViewById(R.id.tv);

        Bundle bundle = getIntent().getExtras();

        String id = bundle.getString("id");
        BmobQuery<ReserveInfo> query = new BmobQuery<>();
        query.getObject(id, new QueryListener<ReserveInfo>() {
            @Override
            public void done(ReserveInfo reserveInfo, BmobException e) {
                if (e == null) {
                    info = reserveInfo;
                    tvNum.setText("" + info.getNumber());
                    if (info.getBuild() == Constant.BUILD_ZX){

                        tvBuild.setText("正心楼");
                    }else if (info.getBuild() == Constant.BUILD_ZZ){
                        tvBuild.setText("致知楼");
                    }else{
                        tvBuild.setText("诚意楼");
                    }
                    String time = "";
                    switch (info.getTime()) {
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
                    Calendar calendar = Calendar.getInstance();
                    tvTime.setText(time);
                    tvDate.setText(""+calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+(calendar.get(Calendar.DATE)+info.getDate()));
                    tvEmail.setText(info.getEmail());
                    tvReason.setText(info.getReason());
                    String state = "";
                    switch (info.getState()) {
                        case Constant.STATE_PASS:
                            state = state + "通过";
                            btnAgree.setVisibility(View.INVISIBLE);
                            btnReject.setVisibility(View.INVISIBLE);
                            tv.setVisibility(View.VISIBLE);
                            tv.setText("已通过");
                            break;
                        case Constant.STATE_UNCHECKED:
                            state = state + "待审核";
                            break;
                        case Constant.STATE_REJECTED:
                            state = state + "未通过";
                            btnAgree.setVisibility(View.INVISIBLE);
                            btnReject.setVisibility(View.INVISIBLE);
                            tv.setVisibility(View.VISIBLE);
                            tv.setText("已拒绝");
                            break;
                        default:
                            break;
                    }

                } else {
                    ToastUtil.showToast(CheckReserveActivity.this, "出错了...");
                }
            }
        });


        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final BmobQuery<ClassRoom> query = new BmobQuery<ClassRoom>();
                query.addWhereEqualTo("number", Integer.parseInt(tvNum.getText().toString()));
                query.addWhereEqualTo("date",info.getDate());
                query.addWhereEqualTo("building",info.getBuild());
                query.findObjects(new FindListener<ClassRoom>() {
                    @Override
                    public void done(List<ClassRoom> list, BmobException e) {
                        if (e == null) {
                            final ClassRoom classRoom = list.get(0);

                            boolean isOccupied = false;

                            switch (info.getTime()) {
                                case 0:
                                    if (classRoom.getState12() == ClassRoom.OCCUPIED) {
                                        isOccupied = true;
                                    }
                                    break;
                                case 1:
                                    if (classRoom.getState34() == ClassRoom.OCCUPIED) {
                                        isOccupied = true;
                                    }
                                    break;
                                case 2:
                                    if (classRoom.getState56() == ClassRoom.OCCUPIED) {
                                        isOccupied = true;
                                    }
                                    break;
                                case 3:
                                    if (classRoom.getState78() == ClassRoom.OCCUPIED) {
                                        isOccupied = true;
                                    }
                                    break;
                                case 4:
                                    if (classRoom.getState910() == ClassRoom.OCCUPIED) {
                                        isOccupied = true;
                                    }
                                    break;
                                default:
                                    break;
                            }
                            if (isOccupied) {
                                new AlertDialog.Builder(CheckReserveActivity.this)
                                        .setTitle("提醒")
                                        .setMessage("该教室已被占用")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                            }
                                        })
                                        .show();

                            } else {
                                switch (info.getTime()) {
                                    case 0:
                                        classRoom.setState12(ClassRoom.OCCUPIED);
                                        break;
                                    case 1:
                                        classRoom.setState34(ClassRoom.OCCUPIED);
                                        break;
                                    case 2:
                                        classRoom.setState56(ClassRoom.OCCUPIED);
                                        break;
                                    case 3:
                                        classRoom.setState78(ClassRoom.OCCUPIED);
                                        break;
                                    case 4:
                                        classRoom.setState910(ClassRoom.OCCUPIED);
                                        break;
                                    default:
                                        break;
                                }
                                classRoom.update(classRoom.getObjectId(), new UpdateListener() {
                                    @Override
                                    public void done(BmobException e) {
                                        if (e == null){

                                            //把相同的申请自动删掉
                                            BmobQuery<ReserveInfo> query1 = new BmobQuery<>();
                                            query1.addWhereEqualTo("number",info.getNumber());
                                            query1.addWhereEqualTo("date",info.getDate());
                                            query1.addWhereEqualTo("time",info.getTime());
                                            query1.addWhereEqualTo("build",info.getBuild());
                                            query1.findObjects(new FindListener<ReserveInfo>() {
                                                @Override
                                                public void done(List<ReserveInfo> list, BmobException e) {
                                                    List<BmobObject> reserveinfos = new ArrayList<>();
                                                    for (int i=0;i<list.size();i++){

                                                        ReserveInfo reserveInfo = new ReserveInfo();
                                                        reserveInfo.setObjectId(list.get(i).getObjectId());
                                                        if (!reserveInfo.getObjectId().equals(info.getObjectId())){
                                                            reserveinfos.add(reserveInfo);
                                                        }

                                                    }
                                                    new BmobBatch().deleteBatch(reserveinfos).doBatch(new QueryListListener<BatchResult>() {
                                                        @Override
                                                        public void done(List<BatchResult> list, BmobException e) {
                                                            if (e == null){
                                                                ToastUtil.showToast(CheckReserveActivity.this,"重复申请已删除");
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                            info.setState(Constant.STATE_PASS);
                                            info.update(info.getObjectId(), new UpdateListener() {
                                                @Override
                                                public void done(BmobException e) {
                                                    if (e == null) {
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



                                                    } else {
                                                        ToastUtil.showToast(CheckReserveActivity.this, "出错了...");
                                                    }
                                                }
                                            });
                                        }else{
                                            ToastUtil.showToast(CheckReserveActivity.this, "出错了...");

                                        }

                                    }
                                });


                            }
                        } else {
                            ToastUtil.showToast(CheckReserveActivity.this, "出错了");
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
                        if (e == null) {
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
                        } else {
                            ToastUtil.showToast(CheckReserveActivity.this, "出错了...");
                        }
                    }
                });


            }
        });

    }


    public void initActionBarView(){
        actionBarView = (ActionBarView) findViewById(R.id.action_bar);
        actionBarView.setOnBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        actionBarView.setTitle("预约信息");
    }
}
