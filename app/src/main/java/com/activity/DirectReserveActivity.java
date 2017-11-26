package com.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.Constant;
import com.model.ReserveInfo;
import com.util.PreferenceUtil;
import com.util.ToastUtil;
import com.view.ActionBarView;

import java.util.Calendar;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class DirectReserveActivity extends AppCompatActivity {

    private ActionBarView actionBarView;

    private TextView tvNum;
    private TextView tvClass;
    private TextView tvBuild;
    private TextView tvTime;

    private EditText etReason;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_reserve);
        initActionBarView();

        Bundle bundle = getIntent().getExtras();
        final int number = bundle.getInt("number");
        final int class_state = bundle.getInt("class");
        final int time = bundle.getInt("time");
        final int build = bundle.getInt("build");

        tvNum = (TextView) findViewById(R.id.tv_num);
        tvClass = (TextView) findViewById(R.id.tv_class);
        tvBuild = (TextView) findViewById(R.id.tv_build);

        tvTime = (TextView) findViewById(R.id.tv_time);
        etReason = (EditText) findViewById(R.id.et_reason);

        tvNum.setText(number + "");
        switch (class_state) {
            case 0:
                tvClass.setText("1-2节");
                break;
            case 1:
                tvClass.setText("3-4节");
                break;
            case 2:
                tvClass.setText("5-6节");
                break;
            case 3:
                tvClass.setText("7-8节");
                break;
            case 4:
                tvClass.setText("9-10节");
                break;
            default:
                break;
        }
        switch (build) {
            case Constant.BUILD_ZX:
                tvBuild.setText("正心楼");
                break;
            case Constant.BUILD_ZZ:
                tvBuild.setText("致知楼");
                break;
            case Constant.BUILD_CY:
                tvBuild.setText("诚意楼");
                break;
            default:
                break;
        }
        final Calendar calendar = Calendar.getInstance();
        final String date = "" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + ((calendar.get(Calendar.DATE) + time));

        tvTime.setText(date);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!etReason.getText().toString().equals("")) {
                    ReserveInfo info = new ReserveInfo(number, class_state, time, etReason.getText().toString()
                            , PreferenceUtil.getData(DirectReserveActivity.this, "userInfo", "email"), Constant.STATE_UNCHECKED);

                    info.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                new AlertDialog.Builder(DirectReserveActivity.this)
                                        .setTitle("提醒")
                                        .setMessage("预约消息发送成功")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                finish();
                                            }
                                        })
                                        .show();
                            } else {
                                ToastUtil.showToast(DirectReserveActivity.this, "出错了...");
                            }
                        }
                    });
                }
            }
        });

    }

    private void initActionBarView(){
        actionBarView = (ActionBarView) findViewById(R.id.action_bar);
        actionBarView.setOnBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        actionBarView.getMore().setVisibility(View.INVISIBLE);


    }
}
