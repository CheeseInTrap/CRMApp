package com.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
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
                return titles[position];
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
