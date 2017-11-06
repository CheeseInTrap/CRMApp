package com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.crmapp.R;
import com.section.SectionAdapter;
import com.section.SectionLayoutManager;
import com.section.SectionListView;
import com.section.SectionViewFactroy;

/**
 * created by zjr on 2017/5/22.
 */

public abstract class BaseSectionFragment extends BaseFragment {

    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected SectionListView mListView;

    protected GridLayoutManager mLayoutManager;
    protected SectionAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_section_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setEnabled(false);

        mListView = (SectionListView) view.findViewById(R.id.section_list);
        mAdapter = new SectionAdapter(getViewFactory());
        mLayoutManager = new SectionLayoutManager(getActivity());
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.getSpan(position);
            }
        });
        mListView.setAdapter(mAdapter);
        mListView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateContent();
    }

    protected abstract SectionViewFactroy getViewFactory();

    protected abstract void updateContent();
}
