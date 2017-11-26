package com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.Constant;
import com.model.ReserveInfo;
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



    private int[] pics = new int[]{R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5};
    private String[] titles = new String[]{"校园地图", "教室查询", "教室预约", "教室推荐", "预约信息"};

    private int[] icons = new int[]{R.drawable.mine_fill,R.drawable.mail_fill,R.drawable.integral_fill,R.drawable.lock_fill
            ,R.drawable.undo};
    private int[] colors = new int[]{R.drawable.funbg_1,R.drawable.funbg_2,R.drawable.funbg_3,R.drawable.funbg_4,R.drawable.funbg_5};



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

        //imageView.setImageResource(R.drawable.main);
        List<String> contents = new ArrayList<>();
        contents.add(PreferenceUtil.getData(this,"userInfo","username"));
        contents.add(PreferenceUtil.getData(this,"userInfo","email"));
        contents.add(""+PreferenceUtil.getInt(this,"userInfo","level"));
        contents.add("管理员");
        contents.add("退出登录");


        ListAdapter adapter = new ListAdapter(contents);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 3:
                        startActivity(new Intent(MainActivity.this,AdminActivity.class));
                        break;
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

                //vh.getCardView().setCardBackgroundColor(colors[position]);
                vh.getImageView().setImageResource(pics[position]);
                vh.getImageView().setBackgroundResource(colors[position]);
                vh.getTextView().setText(titles[position]);
                vh.getTextView().setBackgroundResource(colors[position]);
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

        actionBarView.setBack(R.drawable.document_fill);
        actionBarView.setMore(R.drawable.more);
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img);
            textView = (TextView) itemView.findViewById(R.id.tv);

            cardView = (CardView) itemView.findViewById(R.id.cardView);

        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public CardView getCardView() {
            return cardView;
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
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.Type,Constant.BUILD_ZX);
                intent = new Intent(this, CRQueryActivity.class);
                intent.putExtras(bundle);
                break;
            case 2:
                intent = new Intent(this, CRReserveActivity.class);
                break;
            case 3:
                intent = new Intent(this, CRRecommendActivity.class);
                break;
            case 4:
                intent = new Intent(this, ReserveInfoActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }


    class ListAdapter extends BaseAdapter{
        private List<String> contents;

        ListAdapter(List<String> contents){
            this.contents = contents;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.drawer_list_item,null,false);
            ImageView img = (ImageView) view.findViewById(R.id.img);
            TextView tv = (TextView) view.findViewById(R.id.tv);

            img.setImageResource(icons[position]);
            tv.setText(contents.get(position));
            return view;
        }
    }


}
