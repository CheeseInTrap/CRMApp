package com.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.crmapp.R;
import com.view.ActionBarView;

/**
 * 作者 ： Created by zjr on 2017/11/6 22:31.
 */

public class SimpleMapFragment extends BaseFragment{

    private ActionBarView actionBarView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_simple,container,false);



        return view;
    }




}
