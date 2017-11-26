package com.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.Constant;
import com.model.ReserveInfo;
import com.util.PreferenceUtil;
import com.util.ToastUtil;
import com.util.db.MySQLiteHelper;
import com.view.ActionBarView;

import java.util.Calendar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CRReserveActivity extends AppCompatActivity {

    private ActionBarView actionBarView;

    private EditText etCRNum;
    private Spinner spTime;
    private TextView tvTime;
    private EditText etReason;
    private Button btn;


    private ArrayAdapter<String> adapter;
    private String[] choices = new String[]{"1-2节","3-4节","5-6节","7-8节","9-10节"};


    private static int time;
    private static int isTimeSel = 0;
    private static int year_sel;
    private static int month_sel;
    private static int date_sel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crreserve);
        initActionBarView();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,choices);
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);


        etCRNum = (EditText) findViewById(R.id.etCRNum);
        spTime = (Spinner) findViewById(R.id.sp_time);
        tvTime = (TextView) findViewById(R.id.tv_time);
        etReason = (EditText) findViewById(R.id.et_reason);
        btn = (Button) findViewById(R.id.btn);


        spTime.setAdapter(adapter);

        spTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time = position;
                isTimeSel = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Calendar calendar = Calendar.getInstance();
        tvTime.setText(String.format("%d--%d--%d",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DATE)));


        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CRReserveActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        tvTime.setText(String.format("%d--%d--%d",year,month+1,dayOfMonth));
                        year_sel = year;
                        month_sel = month;
                        date_sel = dayOfMonth;
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE)).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int CRNum = -1;
                if (etCRNum.getText().toString().equals("")){
                }else{
                    CRNum = Integer.parseInt(etCRNum.getText().toString());
                }

                String reason = etReason.getText().toString();

                Log.v("教室预约",""+CRNum);
                Log.v("教室预约",""+time);

                Log.v("教室预约",reason);

                if (isTimeSel == 1 && CRNum!=-1 && !reason.equals("")){
                    if (year_sel == 0 || month_sel == 0 || date_sel ==0){
                        ToastUtil.showToast(CRReserveActivity.this,"请选择预约日期");
                    }else if(year_sel!=calendar.get(Calendar.YEAR) || month_sel!=calendar.get(Calendar.MONTH)|| Math.abs(date_sel-calendar.get(Calendar.DATE))>3){
                        ToastUtil.showToast(CRReserveActivity.this,"只能预约三天以内的教室");
                    }else{

                        ReserveInfo info = new ReserveInfo(CRNum,time,date_sel-calendar.get(Calendar.DATE),reason
                                ,PreferenceUtil.getData(CRReserveActivity.this,"userInfo","email"),Constant.STATE_UNCHECKED);

                        info.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null){
                                    new AlertDialog.Builder(CRReserveActivity.this)
                                            .setTitle("提醒")
                                            .setMessage("预约消息发送成功")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    finish();
                                                }
                                            })
                                            .show();
                                }else{
                                    ToastUtil.showToast(CRReserveActivity.this,"出错了...");
                                }
                            }
                        });
                    }


                }else {
                    ToastUtil.showToast(CRReserveActivity.this,"请确认信息填写完整");
                }

            }
        });


    }

    private void initActionBarView(){

        actionBarView = (ActionBarView) findViewById(R.id.action_bar);
        actionBarView.setOnBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        actionBarView.setOnMoreClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(CRReserveActivity.this,"more");
            }
        });
    }

}
