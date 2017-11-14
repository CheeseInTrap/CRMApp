package com.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import com.view.ActionBarView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class ReserveInfoActivity extends AppCompatActivity {


    private RecyclerView rv;
    private ActionBarView actionBarView;

    private static List<ReserveInfo> infos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_info);


        initActionBarView();



        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        BmobQuery<ReserveInfo> query = new BmobQuery<>();
        query.addWhereEqualTo("email",PreferenceUtil.getData(this,"userInfo","email"));
        query.findObjects(new FindListener<ReserveInfo>() {
            @Override
            public void done(final List<ReserveInfo> list, BmobException e) {
                if(e == null){
                    infos = list;
                    rv.setAdapter(new RecyclerView.Adapter() {
                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                            return new ViewHolder(LayoutInflater.from(ReserveInfoActivity.this).inflate(R.layout.reserve_list_item,null,false));
                        }

                        @Override
                        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                            ViewHolder vh = (ViewHolder) holder;

                            ReserveInfo info = list.get(position);
                            ImageView img = vh.getImg();
                            TextView tvNum = vh.getTvNum();
                            TextView tvTime = vh.getTvTime();
                            TextView tvState = vh.getTvState();

                            int color = 0;
                            switch (info.getState()){
                                case Constant.STATE_PASS:
                                    img.setImageResource(R.drawable.pass);
                                    tvState.setText("通过");
                                    color = R.color.main_3;
                                    break;
                                case Constant.STATE_UNCHECKED:
                                    img.setImageResource(R.drawable.feedback_fill);
                                    tvState.setText("待审核");
                                    color = R.color.main_2;
                                    break;
                                case Constant.STATE_REJECTED:
                                    img.setImageResource(R.drawable.reject);
                                    tvState.setText("未通过");
                                    color = R.color.main_1;
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
                            return list.size();
                        }
                    });

                }else {
                    ToastUtil.showToast(ReserveInfoActivity.this,"出错了");
                }
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
                finish();

            }
        });
    }

    public void onItemClick(View view){

        int position = rv.getChildAdapterPosition(view);
        Intent intent = new Intent(ReserveInfoActivity.this,ReserveDetailActivity.class);
        Bundle bundle = new Bundle();
        ReserveInfo info = infos.get(position);
        bundle.putString("id",info.getObjectId());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
