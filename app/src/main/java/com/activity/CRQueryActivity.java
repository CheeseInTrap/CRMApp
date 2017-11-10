package com.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

import java.util.ArrayList;

public class CRQueryActivity extends AppCompatActivity {

    private RecyclerView rvCR;
    private CardView cardView;

    private ActionBarView actionBarView;

    private ArrayList<ClassRoom> classRooms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crquery);

        initActionBarView();

        //先模拟一下数据
        for (int i=0;i<10;i++){
            classRooms.add(new ClassRoom(101,1,1,1,0,1,0,0));
        }


        rvCR = (RecyclerView) findViewById(R.id.rv_classroom);
        //cardView = (CardView) findViewById(R.id.cv_list_item);


        rvCR.setLayoutManager(new LinearLayoutManager(this));
        rvCR.setAdapter(new RecyclerView.Adapter() {


            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                return new ViewHolder(LayoutInflater.from(CRQueryActivity.this).inflate(R.layout.list_item,null,false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ViewHolder vh = (ViewHolder) holder;
                ClassRoom cr = classRooms.get(position);

                vh.getTvNum().setText(""+cr.getNumber());
                if (cr.getState12() == ClassRoom.OCCUPIED){

                    vh.getImbtn12().setImageResource(android.R.drawable.ic_input_add);
                }
                if (cr.getState34() == ClassRoom.OCCUPIED){

                    vh.getImbtn34().setImageResource(android.R.drawable.ic_input_add);
                }
                if (cr.getState56() == ClassRoom.OCCUPIED){

                    vh.getImbtn56().setImageResource(android.R.drawable.ic_input_add);
                }
                if (cr.getState78() == ClassRoom.OCCUPIED){

                    vh.getImbtn78().setImageResource(android.R.drawable.ic_input_add);
                }
                if (cr.getState910() == ClassRoom.OCCUPIED){

                    vh.getImbtn910().setImageResource(android.R.drawable.ic_input_add);
                }
            }

            @Override
            public int getItemCount() {
                if (classRooms.size()<=10){
                    return classRooms.size();
                }
                return 10;
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{


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
                ToastUtil.showToast(CRQueryActivity.this,"more");
            }
        });
    }
}
