package com.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class AdminActivity extends AppCompatActivity {

    private ActionBarView actionBarView;
    private RecyclerView rv;

    private static ArrayList<ReserveInfo> infos;
    private static RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initActionBarView();


        MySQLiteHelper helper = new MySQLiteHelper(this);
        SQLiteDatabase reader = helper.getReadableDatabase();
        Cursor c = reader.query("reserveInfo",null,"state=?",new String[]{""+Constant.STATE_UNCHECKED},null,null,null);
        infos = new ArrayList<ReserveInfo>();
        while(c.moveToNext()){
            ReserveInfo info = new ReserveInfo(c.getInt(c.getColumnIndex("_id"))
                    ,c.getInt(c.getColumnIndex("number"))
                    , c.getInt(c.getColumnIndex("time"))
                    ,c.getInt(c.getColumnIndex("year"))
                    ,c.getInt(c.getColumnIndex("month"))
                    ,c.getInt(c.getColumnIndex("date"))
                    ,c.getString(c.getColumnIndex("reason"))
                    ,c.getString(c.getColumnIndex("email"))
                    ,c.getInt(c.getColumnIndex("state")));

            infos.add(info);
        }


        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(AdminActivity.this).inflate(
                        R.layout.admin_reserve_list_item,null,false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


                ViewHolder vh = (ViewHolder) holder;
                ReserveInfo info = infos.get(position);
                TextView tvNum = vh.getTvNum();
                TextView tvTime = vh.getTvTime();
                tvNum.setText(info.getNumber()+"");
                tvTime.setText(""+info.getYear()+"-"+info.getMonth()+"-"+info.getDate());

            }

            @Override
            public int getItemCount() {
                return infos.size();
            }
        };
        rv.setAdapter(adapter);

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView tvNum;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            tvNum = (TextView) itemView.findViewById(R.id.tv_num);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
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
        Intent intent = new Intent(AdminActivity.this,CheckReserveActivity.class);
        Bundle bundle = new Bundle();
        ReserveInfo info = infos.get(position);
        bundle.putInt("id",info.getId());
        bundle.putInt("number",info.getNumber());
        bundle.putInt("time",info.getTime());
        bundle.putInt("year",info.getYear());
        bundle.putInt("month",info.getMonth());
        bundle.putInt("date",info.getDate());
        bundle.putString("reason",info.getReason());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MySQLiteHelper helper = new MySQLiteHelper(this);
        SQLiteDatabase reader = helper.getReadableDatabase();
        Cursor c = reader.query("reserveInfo",null,"state=?",new String[]{""+Constant.STATE_UNCHECKED},null,null,null);
        infos.clear();
        while(c.moveToNext()){
            ReserveInfo info = new ReserveInfo(c.getInt(c.getColumnIndex("_id"))
                    ,c.getInt(c.getColumnIndex("number"))
                    , c.getInt(c.getColumnIndex("time"))
                    ,c.getInt(c.getColumnIndex("year"))
                    ,c.getInt(c.getColumnIndex("month"))
                    ,c.getInt(c.getColumnIndex("date"))
                    ,c.getString(c.getColumnIndex("reason"))
                    ,c.getString(c.getColumnIndex("email"))
                    ,c.getInt(c.getColumnIndex("state")));

            infos.add(info);
        }

        adapter.notifyDataSetChanged();
    }
}
