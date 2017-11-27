package com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.ClassRoom;
import com.model.Constant;
import com.util.ToastUtil;
import com.view.ActionBarView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by asus on 2017/11/12.
 */

public class CRRecresultActivity extends AppCompatActivity {


    private ActionBarView actionBarView;
    private RecyclerView rvCR;
    private RadioButton date1;
    private RadioButton date2;
    private RadioButton date3;
    private RadioGroup group;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crquery);
        initActionBarView();

        rvCR = (RecyclerView) findViewById(R.id.rv_classroom);
        rvCR.setLayoutManager(new LinearLayoutManager(this));


        group = (RadioGroup) findViewById(R.id.group);
        date1 = (RadioButton) findViewById(R.id.date1);
        date2 = (RadioButton) findViewById(R.id.date2);
        date3 = (RadioButton) findViewById(R.id.date3);
        date1.setChecked(true);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (date1.isChecked()) {
                    setSituation(0);
                } else if (date2.isChecked()) {
                    setSituation(1);
                } else if (date3.isChecked()) {
                    setSituation(2);
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        setSituation(0);

    }

    private void setSituation(int date){
        Bundle bundle = getIntent().getExtras();

        int floor = bundle.getInt("floor");
        int size = bundle.getInt("size");
        int build = bundle.getInt("build");

        BmobQuery<ClassRoom> query = new BmobQuery<>();
        if (floor!=0){

            query.addWhereEqualTo("floor",floor);
        }
        if (size!=-1){

            query.addWhereEqualTo("size",size);
        }
        if (build!=-1){
            query.addWhereEqualTo("building",build);
        }
        if (date!=-1){
            query.addWhereEqualTo("date",date);
        }
        query.findObjects(new FindListener<ClassRoom>() {
            @Override
            public void done(final List<ClassRoom> list, BmobException e) {
                if (e == null){
                    rvCR.setAdapter(new RecyclerView.Adapter() {


                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                            return new ViewHolder(LayoutInflater.from(CRRecresultActivity.this).inflate(R.layout.list_item, null, false));
                        }

                        @Override
                        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                            ViewHolder vh = (ViewHolder) holder;
                            final ClassRoom cr = list.get(position);

                            vh.getTvNum().setText("" + cr.getNumber());

                            int color = 0;
                            switch (cr.getSize()){
                                case Constant.SIZE_SMALL:
                                    color = R.color.color_2;
                                    break;
                                case Constant.SIZE_MEDIUM:
                                    color = R.color.color_4;
                                    break;
                                case Constant.SIZE_LARGE:
                                    color = R.color.color_3;
                                    break;
                                default:
                                    break;
                            }
                            vh.getTvNum().setBackgroundResource(color);
                            vh.getTvNum().setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(CRRecresultActivity.this, SingleClassRoomActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("number", cr.getNumber());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });

                            if (cr.getState12() == ClassRoom.UNOCCUPIED) {

                                vh.getImbtn12().setImageResource(R.color.main_4);
                                vh.getImbtn12().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(CRRecresultActivity.this, DirectReserveActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("number", list.get(position).getNumber());
                                        bundle.putInt("class", 0);
                                        bundle.putInt("time", list.get(position).getDate());
                                        bundle.putInt("build", list.get(position).getBuilding());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }
                            if (cr.getState34() == ClassRoom.UNOCCUPIED) {

                                vh.getImbtn34().setImageResource(R.color.main_4);
                                vh.getImbtn34().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(CRRecresultActivity.this, DirectReserveActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("number", list.get(position).getNumber());
                                        bundle.putInt("class", 1);
                                        bundle.putInt("time", list.get(position).getDate());
                                        bundle.putInt("build", list.get(position).getBuilding());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }
                            if (cr.getState56() == ClassRoom.UNOCCUPIED) {

                                vh.getImbtn56().setImageResource(R.color.main_4);
                                vh.getImbtn56().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(CRRecresultActivity.this, DirectReserveActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("number", list.get(position).getNumber());
                                        bundle.putInt("class", 2);
                                        bundle.putInt("time", list.get(position).getDate());
                                        bundle.putInt("build", list.get(position).getBuilding());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }
                            if (cr.getState78() == ClassRoom.UNOCCUPIED) {

                                vh.getImbtn78().setImageResource(R.color.main_4);

                                vh.getImbtn78().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(CRRecresultActivity.this, DirectReserveActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("number", list.get(position).getNumber());
                                        bundle.putInt("class", 3);
                                        bundle.putInt("time", list.get(position).getDate());
                                        bundle.putInt("build", list.get(position).getBuilding());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }
                            if (cr.getState910() == ClassRoom.UNOCCUPIED) {

                                vh.getImbtn910().setImageResource(R.color.main_4);
                                vh.getImbtn910().setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(CRRecresultActivity.this, DirectReserveActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("number", list.get(position).getNumber());
                                        bundle.putInt("class", 4);
                                        bundle.putInt("time", list.get(position).getDate());
                                        bundle.putInt("build", list.get(position).getBuilding());
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }

                        @Override
                        public int getItemCount() {
                            return list.size();

                        }
                    });

                }
                else {
                    ToastUtil.showToast(CRRecresultActivity.this,"出错了...");
                }

            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView tvNum;
        private ImageView imbtn12;
        private ImageView imbtn34;
        private ImageView imbtn56;
        private ImageView imbtn78;
        private ImageView imbtn910;


        public ViewHolder(View itemView) {
            super(itemView);


            tvNum = (TextView) itemView.findViewById(R.id.tvNum);
            imbtn12 = (ImageView) itemView.findViewById(R.id.imbtn_12);
            imbtn34 = (ImageView) itemView.findViewById(R.id.imbtn_34);
            imbtn56 = (ImageView) itemView.findViewById(R.id.imbtn_56);
            imbtn78 = (ImageView) itemView.findViewById(R.id.imbtn_78);
            imbtn910 = (ImageView) itemView.findViewById(R.id.imbtn_910);
        }

        public TextView getTvNum() {
            return tvNum;
        }

        public ImageView getImbtn12() {
            return imbtn12;
        }

        public ImageView getImbtn34() {
            return imbtn34;
        }

        public ImageView getImbtn56() {
            return imbtn56;
        }

        public ImageView getImbtn78() {
            return imbtn78;
        }

        public ImageView getImbtn910() {
            return imbtn910;
        }
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
