package com.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.ClassRoom;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crquery);
        initActionBarView();

        rvCR = (RecyclerView) findViewById(R.id.rv_classroom);
        rvCR.setLayoutManager(new LinearLayoutManager(this));


        Bundle bundle = getIntent().getExtras();

        int floor = bundle.getInt("floor");
        int size = bundle.getInt("size");

        BmobQuery<ClassRoom> query = new BmobQuery<>();
        if (floor!=0){

            query.addWhereEqualTo("floor",floor);
        }
        if (size!=-1){

            query.addWhereEqualTo("size",size);
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
                        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                            ViewHolder vh = (ViewHolder) holder;
                            ClassRoom cr = list.get(position);

                            vh.getTvNum().setText("" + cr.getNumber());
                            if (cr.getState12() == ClassRoom.OCCUPIED) {

                                vh.getImbtn12().setImageResource(android.R.drawable.ic_input_add);
                            }
                            if (cr.getState34() == ClassRoom.OCCUPIED) {

                                vh.getImbtn34().setImageResource(android.R.drawable.ic_input_add);
                            }
                            if (cr.getState56() == ClassRoom.OCCUPIED) {

                                vh.getImbtn56().setImageResource(android.R.drawable.ic_input_add);
                            }
                            if (cr.getState78() == ClassRoom.OCCUPIED) {

                                vh.getImbtn78().setImageResource(android.R.drawable.ic_input_add);
                            }
                            if (cr.getState910() == ClassRoom.OCCUPIED) {

                                vh.getImbtn910().setImageResource(android.R.drawable.ic_input_add);
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
        private ImageButton imbtn12;
        private ImageButton imbtn34;
        private ImageButton imbtn56;
        private ImageButton imbtn78;
        private ImageButton imbtn910;


        public ViewHolder(View itemView) {
            super(itemView);


            tvNum = (TextView) itemView.findViewById(R.id.tvNum);
            imbtn12 = (ImageButton) itemView.findViewById(R.id.imbtn_12);
            imbtn34 = (ImageButton) itemView.findViewById(R.id.imbtn_34);
            imbtn56 = (ImageButton) itemView.findViewById(R.id.imbtn_56);
            imbtn78 = (ImageButton) itemView.findViewById(R.id.imbtn_78);
            imbtn910 = (ImageButton) itemView.findViewById(R.id.imbtn_910);
        }

        public TextView getTvNum() {
            return tvNum;
        }

        public ImageButton getImbtn12() {
            return imbtn12;
        }

        public ImageButton getImbtn34() {
            return imbtn34;
        }

        public ImageButton getImbtn56() {
            return imbtn56;
        }

        public ImageButton getImbtn78() {
            return imbtn78;
        }

        public ImageButton getImbtn910() {
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
