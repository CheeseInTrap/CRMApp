package com.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
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
import com.util.ToastUtil;
import com.util.db.MySQLiteHelper;
import com.view.ActionBarView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class AdminActivity extends AppCompatActivity {

    private ActionBarView actionBarView;
    private RecyclerView rv;

    private static List<ReserveInfo> infos;
    private static RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initActionBarView();

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img1;
        private ImageView img2;
        private TextView tvNum;
        private TextView tvTime;
        private TextView tvState;

        public ViewHolder(View itemView) {
            super(itemView);
            img1 = (ImageView) itemView.findViewById(R.id.img1);
            img2 = (ImageView) itemView.findViewById(R.id.img2);
            tvNum = (TextView) itemView.findViewById(R.id.tv_num);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            tvState = (TextView) itemView.findViewById(R.id.tv_state);
        }

        public ImageView getImg1() {
            return img1;
        }

        public ImageView getImg2() {
            return img2;
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
        Intent intent = new Intent(AdminActivity.this,CheckReserveActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("id",infos.get(position).getObjectId());
        intent.putExtras(bundle);
        startActivity(intent);
    }



    @Override
    protected void onResume() {
        super.onResume();
        BmobQuery<ReserveInfo> query = new BmobQuery<>();
        query.findObjects(new FindListener<ReserveInfo>() {
            @Override
            public void done(List<ReserveInfo> list, BmobException e) {

                if (e == null){
                    infos = list;
                    adapter = new RecyclerView.Adapter() {
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                            return new ViewHolder(LayoutInflater.from(AdminActivity.this).inflate(
                                    R.layout.admin_reserve_list_item,null,false));
                        }

                        @Override
                        public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {


                            ViewHolder vh = (ViewHolder) holder;
                            ReserveInfo info = infos.get(position);
                            ImageView img1 = vh.getImg1();
                            ImageView img2 = vh.getImg2();
                            TextView tvNum = vh.getTvNum();
                            TextView tvTime = vh.getTvTime();
                            TextView tvState = vh.getTvState();
                            tvNum.setText(info.getNumber()+"");
                            tvTime.setText(""+info.getYear()+"-"+info.getMonth()+"-"+info.getDate());


                            int color = 0;
                            switch (info.getState()){
                                case Constant.STATE_PASS:
                                    img1.setImageResource(R.drawable.pass);
                                    tvState.setText("通过");
                                    color = R.color.main_3;
                                    break;
                                case Constant.STATE_UNCHECKED:
                                    img1.setImageResource(R.drawable.feedback_fill);
                                    tvState.setText("待审核");
                                    color = R.color.main_2;
                                    break;
                                case Constant.STATE_REJECTED:
                                    img1.setImageResource(R.drawable.reject);
                                    tvState.setText("未通过");
                                    color = R.color.main_1;
                                    break;
                                default:
                                    break;
                            }
                            tvNum.setText(""+info.getNumber());
                            tvTime.setText(""+info.getYear()+"-"+info.getMonth()+"-"+info.getDate());

                            img1.setBackgroundResource(color);
                            img2.setBackgroundResource(color);
                            tvNum.setBackgroundResource(color);
                            tvTime.setBackgroundResource(color);
                            tvNum.setBackgroundResource(color);
                            tvState.setBackgroundResource(color);

                            img2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ReserveInfo reserveInfo = new ReserveInfo();
                                    reserveInfo.setObjectId(infos.get(position).getObjectId());
                                    reserveInfo.delete(new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null){
                                                new AlertDialog.Builder(AdminActivity.this)
                                                        .setTitle("提醒")
                                                        .setMessage("删除成功")
                                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                finish();
                                                            }
                                                        })
                                                        .show();
                                                onResume();
                                            }else{
                                                ToastUtil.showToast(AdminActivity.this,"删除失败");
                                            }
                                        }
                                    });
                                }
                            });

                        }

                        @Override
                        public int getItemCount() {
                            return infos.size();
                        }
                    };
                    rv.setAdapter(adapter);
                }else {
                    ToastUtil.showToast(AdminActivity.this,"出错了...");
                }
            }
        });

    }
}
