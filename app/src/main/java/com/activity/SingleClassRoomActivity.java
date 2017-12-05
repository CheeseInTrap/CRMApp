package com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.ClassRoom;
import com.model.Constant;
import com.util.DateComparator;
import com.util.ToastUtil;
import com.view.ActionBarView;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SingleClassRoomActivity extends AppCompatActivity {

    private ImageView imbtn_12;
    private ImageView imbtn_34;
    private ImageView imbtn_56;
    private ImageView imbtn_78;
    private ImageView imbtn_910;
    private ImageView imbtn2_12;
    private ImageView imbtn2_34;
    private ImageView imbtn2_56;
    private ImageView imbtn2_78;
    private ImageView imbtn2_910;
    private ImageView imbtn3_12;
    private ImageView imbtn3_34;
    private ImageView imbtn3_56;
    private ImageView imbtn3_78;
    private ImageView imbtn3_910;

    private TextView tvDate1;
    private TextView tvDate2;
    private TextView tvDate3;

    private ActionBarView actionBarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_class_room);
        initActionBarView();
        int number = getIntent().getExtras().getInt("number");
        imbtn_12 = (ImageView) findViewById(R.id.imbtn_12);
        imbtn_34 = (ImageView) findViewById(R.id.imbtn_34);
        imbtn_56 = (ImageView) findViewById(R.id.imbtn_56);
        imbtn_78 = (ImageView) findViewById(R.id.imbtn_78);
        imbtn_910 = (ImageView) findViewById(R.id.imbtn_910);
        imbtn2_12 = (ImageView) findViewById(R.id.imbtn2_12);
        imbtn2_34 = (ImageView) findViewById(R.id.imbtn2_34);
        imbtn2_56 = (ImageView) findViewById(R.id.imbtn2_56);
        imbtn2_78 = (ImageView) findViewById(R.id.imbtn2_78);
        imbtn2_910 = (ImageView) findViewById(R.id.imbtn2_910);
        imbtn3_12 = (ImageView) findViewById(R.id.imbtn3_12);
        imbtn3_34 = (ImageView) findViewById(R.id.imbtn3_34);
        imbtn3_56 = (ImageView) findViewById(R.id.imbtn3_56);
        imbtn3_78 = (ImageView) findViewById(R.id.imbtn3_78);
        imbtn3_910 = (ImageView) findViewById(R.id.imbtn3_910);

        tvDate1 = (TextView) findViewById(R.id.tvDate1);
        tvDate2 = (TextView) findViewById(R.id.tvDate2);
        tvDate3 = (TextView) findViewById(R.id.tvDate3);
        Calendar calendar = Calendar.getInstance();
        String date = ""+calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-";
        tvDate1.setText(date+calendar.get(Calendar.DATE));
        tvDate2.setText(date+(calendar.get(Calendar.DATE)+1));
        tvDate3.setText(date+(calendar.get(Calendar.DATE)+2));

        BmobQuery<ClassRoom> query = new BmobQuery<>();
        query.addWhereEqualTo("number",number);
        query.findObjects(new FindListener<ClassRoom>() {
            @Override
            public void done(List<ClassRoom> list, BmobException e) {
                if (e == null){
                    Collections.sort(list,new DateComparator());
                    ClassRoom classRoom = null;
                    classRoom = list.get(0);
                    String building = "";
                    switch (classRoom.getBuilding()){
                        case Constant.BUILD_ZX:
                            building = building+"正心";
                            break;
                        case Constant.BUILD_ZZ:
                            building = building+"致知";
                            break;
                        case Constant.BUILD_CY:
                            building = building+"诚意";
                            break;

                    }
                    building =  building+" "+classRoom.getNumber();
                    actionBarView.setTitle(building);
                    if (classRoom.getState12() == ClassRoom.UNOCCUPIED){
                        imbtn_12.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState34() == ClassRoom.UNOCCUPIED){
                        imbtn_34.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState56() == ClassRoom.UNOCCUPIED){
                        imbtn_56.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState78() == ClassRoom.UNOCCUPIED){
                        imbtn_78.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState910() == ClassRoom.UNOCCUPIED){
                        imbtn_910.setImageResource(R.color.main_4);
                    }
                    classRoom = list.get(1);

                    if (classRoom.getState12() == ClassRoom.UNOCCUPIED){
                        imbtn2_12.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState34() == ClassRoom.UNOCCUPIED){
                        imbtn2_34.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState56() == ClassRoom.UNOCCUPIED){
                        imbtn2_56.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState78() == ClassRoom.UNOCCUPIED){
                        imbtn2_78.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState910() == ClassRoom.UNOCCUPIED){
                        imbtn2_910.setImageResource(R.color.main_4);
                    }

                    classRoom = list.get(2);

                    if (classRoom.getState12() == ClassRoom.UNOCCUPIED){
                        imbtn3_12.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState34() == ClassRoom.UNOCCUPIED){
                        imbtn3_34.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState56() == ClassRoom.UNOCCUPIED){
                        imbtn3_56.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState78() == ClassRoom.UNOCCUPIED){
                        imbtn3_78.setImageResource(R.color.main_4);
                    }
                    if (classRoom.getState910() == ClassRoom.UNOCCUPIED){
                        imbtn3_910.setImageResource(R.color.main_4);
                    }

                }else {
                    ToastUtil.showToast(SingleClassRoomActivity.this,"出错了");
                }


            }
        });
    }

    private void initActionBarView() {
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
