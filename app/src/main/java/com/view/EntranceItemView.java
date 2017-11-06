package com.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.crmapp.R;
import com.model.EntranceItem;
import com.section.SectionItemView;

/**
 * ckb on 2017/5/22.
 */

public class EntranceItemView extends SectionItemView<EntranceItem> {

    public static final int CLICK_ENTRANCE = 1;

    private TextView mNameText;

    public EntranceItemView(View itemView) {
        super(itemView);

        mNameText = (TextView) itemView.findViewById(R.id.name);
        mNameText.setOnClickListener(this);
    }

    @Override
    public int getSectionType() {
        return ENTRANCE;
    }

    @Override
    protected void updateImpl(EntranceItem model) {
        mNameText.setText(model.getName());
    }

    @Override
    protected void fireClick(View v) {
        if (mNameText == v) {
            mListener.onClickListener(CLICK_ENTRANCE, mViewModle);
        }
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public SectionItemView create(LayoutInflater inflater, ViewGroup parent) {
            return new EntranceItemView(inflater.inflate(R.layout.layout_section_entrance, parent, false));
        }
    };
}
