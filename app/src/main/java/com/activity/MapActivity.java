package com.activity;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;

import com.example.user.crmapp.R;
import com.fragment.BaiduMapFragment;
import com.fragment.SimpleMapFragment;
import com.util.ToastUtil;
import com.view.ActionBarView;
import com.view.noScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    private ActionBarView actionBarView;
    private TabLayout tabLayout;
    private noScrollViewPager viewPager;
    private Context mContext = MapActivity.this;


    public static final String[] titles = new String[]{"校园地图","百度地图"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initActionBarView();
        initView();

    }


    private void initView(){
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (noScrollViewPager) findViewById(R.id.view_pager);

        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SimpleMapFragment());
        fragments.add(new BaiduMapFragment());



        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {

                Drawable drawable =null;
                switch (position) {
                    case 0:
                        drawable = ContextCompat.getDrawable(MapActivity.this, R.drawable.map1);
                        break;
                    case 1:
                        drawable = ContextCompat.getDrawable(MapActivity.this, R.drawable.map2);
                        break;
                    default:
                        break;
                }
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
                SpannableString spannableString = new SpannableString(" "+titles[position]);
                spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                return spannableString;

            }


        });
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int i = tab.getPosition();
                viewPager.setCurrentItem(i,false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

    private void initActionBarView(){
        actionBarView = (ActionBarView) findViewById(R.id.action_bar);
        actionBarView.setOnBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        actionBarView.setOnMoreClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
