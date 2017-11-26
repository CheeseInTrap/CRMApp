package com.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class CRQueryActivity extends AppCompatActivity {

    private RecyclerView rvCR;


    private ActionBarView actionBarView;
    private RadioGroup group;
    private RadioButton date1;
    private RadioButton date2;
    private RadioButton date3;




    private static int build;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crquery);

        build = getIntent().getExtras().getInt(Constant.Type);
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
                if (date1.isChecked()){
                    setSituation(build,0);
                }else if (date2.isChecked()){
                    setSituation(build,1);
                }else if (date3.isChecked()){
                    setSituation(build,2);
                }
            }
        });

        setSituation(build, 0);


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

    private void initActionBarView() {

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
                actionBarView.showContextMenu();
            }
        });

        actionBarView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu,contextMenu);
            }
        });
        switch (build) {
            case Constant.BUILD_ZX:
                actionBarView.setTitle("正心楼");
                break;
            case Constant.BUILD_ZZ:
                actionBarView.setTitle("致知楼");
                break;

            case Constant.BUILD_CY:
                actionBarView.setTitle("诚意楼");
                break;
        }
        actionBarView.setMore(R.drawable.feedback_fill);
    }




    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.zx:
                build = Constant.BUILD_ZX;
                actionBarView.setTitle("正心楼");
                setSituation(build, 0);
                date1.setChecked(true);
                break;
            case R.id.zz:
                build = Constant.BUILD_ZZ;
                setSituation(build, 0);
                actionBarView.setTitle("致知楼");
                date1.setChecked(true);
                break;
            case R.id.cy:
                build = Constant.BUILD_CY;
                setSituation(build, 0);
                actionBarView.setTitle("诚意楼");
                date1.setChecked(true);
                break;
            default:
                break;

        }
        return true;
    }

    private void setSituation(int buildType, int date) {
        BmobQuery<ClassRoom> query = new BmobQuery<>();
        query.addWhereEqualTo("building", buildType);
        query.addWhereEqualTo("date", date);
        query.findObjects(new FindListener<ClassRoom>() {
            @Override
            public void done(final List<ClassRoom> list, BmobException e) {
                if (e == null) {
                    rvCR.setAdapter(new RecyclerView.Adapter() {


                        @Override
                        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                            return new ViewHolder(LayoutInflater.from(CRQueryActivity.this).inflate(R.layout.list_item, null, false));
                        }

                        @Override
                        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                            ViewHolder vh = (ViewHolder) holder;
                            ClassRoom cr = list.get(position);

                            vh.getTvNum().setText("" + cr.getNumber());
                            int color = 0;
                            switch (cr.getSize()) {
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


                            if (cr.getState12() == ClassRoom.OCCUPIED) {

                                vh.getImbtn12().setImageResource(R.color.main_5);
                            }
                            if (cr.getState34() == ClassRoom.OCCUPIED) {

                                vh.getImbtn34().setImageResource(R.color.main_5);
                            }
                            if (cr.getState56() == ClassRoom.OCCUPIED) {

                                vh.getImbtn56().setImageResource(R.color.main_5);
                            }
                            if (cr.getState78() == ClassRoom.OCCUPIED) {

                                vh.getImbtn78().setImageResource(R.color.main_5);
                            }
                            if (cr.getState910() == ClassRoom.OCCUPIED) {

                                vh.getImbtn910().setImageResource(R.color.main_5);
                            }
                        }

                        @Override
                        public int getItemCount() {
                            return list.size();

                        }
                    });

                } else {
                    ToastUtil.showToast(CRQueryActivity.this, "出错了...");
                }
            }
        });
    }
}
