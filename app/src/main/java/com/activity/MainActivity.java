package com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.util.PreferenceUtil;
import com.util.ToastUtil;
import com.view.ActionBarView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjr on 2017/9/27.
 * 功能的主页面，分为“主页”“我的”两个fragment
 */

public class MainActivity extends AppCompatActivity {


    private ActionBarView actionBarView;
    private RecyclerView rv;
    private DrawerLayout drawer;
    private LinearLayout drawerCon;
    private ListView listView;
    private ImageView imageView;

    private ActionBarDrawerToggle toggle;

    private DrawerLayout.DrawerListener listener;



    private int[] pics = new int[]{R.drawable.whale, R.drawable.fox, R.drawable.crab, R.drawable.koala, R.drawable.chick};
    private String[] titles = new String[]{"校园地图", "教室查询", "教室预约", "教室推荐", "预约信息"};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActionBarView();


        rv = (RecyclerView)findViewById(R.id.rv);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerCon = (LinearLayout) findViewById(R.id.drawer_content);
        listView = (ListView) findViewById(R.id.listView);
        imageView = (ImageView) findViewById(R.id.img);

        int level = PreferenceUtil.getInt(this,"userInfo","level");

        switch (level){
            case 1:
                imageView.setImageResource(R.drawable.fox);
                break;
            case 2:
                imageView.setImageResource(R.drawable.koala);
                break;
            case 3:
                imageView.setImageResource(R.drawable.whale);
                break;
            case 4:
                imageView.setImageResource(R.drawable.chick);
                break;
            case 5:
                imageView.setImageResource(R.drawable.bull);
                break;

            default:
                break;
        }

        imageView.setImageResource(R.drawable.main);
        List<String> contents = new ArrayList<>();
        contents.add(PreferenceUtil.getData(this,"userInfo","username"));
        contents.add(PreferenceUtil.getData(this,"userInfo","email"));
        contents.add(""+PreferenceUtil.getInt(this,"userInfo","level"));
        contents.add(""+PreferenceUtil.getInt(this,"userInfo","role"));
        contents.add("退出登录");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contents);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 4:

                        PreferenceUtil.clear(MainActivity.this,"userInfo");
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        finish();
                        break;
                    default:
                        break;
                }
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.main_list_item, null, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                ViewHolder vh = (ViewHolder) holder;

                vh.getImageView().setImageResource(pics[position]);
                vh.getTextView().setText(titles[position]);
            }

            @Override
            public int getItemCount() {
                return pics.length;
            }
        });

        listener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        };

        drawer.addDrawerListener(listener);
    }



    private void initActionBarView(){
        actionBarView = (ActionBarView) findViewById(R.id.action_bar);
        actionBarView.setOnBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(drawerCon);

            }
        });
        actionBarView.setOnMoreClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(MainActivity.this,"more");
            }
        });

        actionBarView.setBack(R.drawable.user_info);
        actionBarView.setMore(R.drawable.more);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.tv);

        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public void onItemClick(View view) {
        int position = rv.getChildAdapterPosition(view);
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(this, MapActivity.class);
                break;
            case 1:
                intent = new Intent(this, CRQueryActivity.class);
                break;
            case 2:
                intent = new Intent(this, CRReserveActivity.class);
                break;
            case 3:
                intent = new Intent(this, CRRecommendActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }


}
