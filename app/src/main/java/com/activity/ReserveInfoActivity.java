package com.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.Constant;
import com.model.ReserveInfo;
import com.util.PreferenceUtil;
import com.util.db.MySQLiteHelper;
import com.view.ActionBarView;

import java.util.ArrayList;
import java.util.List;

public class ReserveInfoActivity extends AppCompatActivity {


    private RecyclerView rv;
    private ActionBarView actionBarView;

    private static List<ReserveInfo> infos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_info);


        initActionBarView();

        MySQLiteHelper helper = new MySQLiteHelper(ReserveInfoActivity.this);
        SQLiteDatabase reader = helper.getReadableDatabase();
        Cursor c = reader.query("reserveInfo",null,"email=?"
                ,new String[]{PreferenceUtil.getData(this,"userInfo","email")}
                ,null,null,null);
        infos = new ArrayList<ReserveInfo>();
        while(c.moveToNext()){
            ReserveInfo info = new ReserveInfo(c.getInt(c.getColumnIndex("_id")),
                    c.getInt(c.getColumnIndex("number")),
                    c.getInt(c.getColumnIndex("time"))
                    ,c.getInt(c.getColumnIndex("year"))
                    ,c.getInt(c.getColumnIndex("month"))
                    ,c.getInt(c.getColumnIndex("date"))
                    ,c.getString(c.getColumnIndex("reason"))
                    ,c.getString(c.getColumnIndex("email"))
                    ,c.getInt(c.getColumnIndex("state")));

            infos.add(info);
        }

        Log.d("infos大小",""+infos.size());

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        rv.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(ReserveInfoActivity.this).inflate(R.layout.reserve_list_item,null,false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ViewHolder vh = (ViewHolder) holder;

                ReserveInfo info = infos.get(position);
                ImageView img = vh.getImg();
                TextView tvNum = vh.getTvNum();
                TextView tvTime = vh.getTvTime();
                TextView tvState = vh.getTvState();

                int color = 0;
                switch (info.getState()){
                    case Constant.STATE_PASS:
                        img.setImageResource(R.drawable.pass);
                        tvState.setText("通过");
                        color = R.color.color_2;
                        break;
                    case Constant.STATE_UNCHECKED:
                        img.setImageResource(R.drawable.wait);
                        tvState.setText("待审核");
                        color = R.color.color_4;
                        break;
                    case Constant.STATE_REJECTED:
                        img.setImageResource(R.drawable.reject);
                        tvState.setText("未通过");
                        color = R.color.color_3;
                        break;
                    default:
                        break;
                }
                tvNum.setText(""+info.getNumber());
                tvTime.setText(""+info.getYear()+"-"+info.getMonth()+"-"+info.getDate());

                img.setBackgroundResource(color);
                tvNum.setBackgroundResource(color);
                tvTime.setBackgroundResource(color);
                tvNum.setBackgroundResource(color);
                tvState.setBackgroundResource(color);


            }

            @Override
            public int getItemCount() {
                return infos.size();
            }
        });
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView tvNum;
        private TextView tvTime;
        private TextView tvState;


        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            tvNum = (TextView) itemView.findViewById(R.id.tv_num);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvState = (TextView) itemView.findViewById(R.id.tv_state);


        }

        public ImageView getImg() {
            return img;
        }

        public TextView getTvNum() {
            return tvNum;
        }

        public TextView getTvTime() {
            return tvTime;
        }

        public TextView getTvState() {
            return tvState;
        }
    }

    public void initActionBarView(){
        actionBarView = (ActionBarView) findViewById(R.id.action_bar);

        actionBarView.setTitle("预约信息");
        actionBarView.setOnMoreClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        actionBarView.setOnBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void onItemClick(View view){

        int position = rv.getChildAdapterPosition(view);
        Intent intent = new Intent(ReserveInfoActivity.this,ReserveDetailActivity.class);
        Bundle bundle = new Bundle();
        ReserveInfo info = infos.get(position);
        bundle.putInt("number",info.getNumber());
        bundle.putInt("time",info.getTime());
        bundle.putInt("year",info.getYear());
        bundle.putInt("month",info.getMonth());
        bundle.putInt("date",info.getDate());
        bundle.putString("reason",info.getReason());
        bundle.putInt("state",info.getState());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
