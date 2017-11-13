package com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.Constant;
import com.model.ReserveInfo;
import com.util.ToastUtil;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class ReserveDetailActivity extends AppCompatActivity {

    private TextView tvNum;
    private TextView tvTime;
    private TextView tvDate;
    private TextView tvReason;
    private TextView tvState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_detail);

        tvNum = (TextView) findViewById(R.id.tv_num);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvReason = (TextView) findViewById(R.id.tv_reason);
        tvState = (TextView) findViewById(R.id.tv_state);

        Bundle bundle = getIntent().getExtras();

        String id = bundle.getString("id");
        BmobQuery<ReserveInfo> query = new BmobQuery<>();
        query.getObject(id, new QueryListener<ReserveInfo>() {
            @Override
            public void done(ReserveInfo reserveInfo, BmobException e) {
                if (e == null){

                    tvNum.setText(""+reserveInfo.getNumber());
                    String time = "";
                    switch (reserveInfo.getTime()){
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
                    tvDate.setText(reserveInfo.getYear()+"-"+(reserveInfo.getMonth()+1)+"-"+reserveInfo.getDate());
                    tvReason.setText(reserveInfo.getReason());
                    String state="";
                    switch (reserveInfo.getState()){
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
                    tvState.setText(state);
                }else {
                    ToastUtil.showToast(ReserveDetailActivity.this,"出错了...");
                }
            }
        });



    }
}
