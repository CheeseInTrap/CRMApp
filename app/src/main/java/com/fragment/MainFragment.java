package com.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import com.activity.CRQueryActivity;
import com.activity.CRReserveActivity;
import com.activity.MapActivity;
import com.model.Constant;
import com.section.Section;
import com.section.SectionItemView;
import com.section.SectionViewFactroy;
import com.model.EntranceItem;
import com.util.ToastUtil;
import com.view.EntranceItemView;

import java.util.ArrayList;
import java.util.List;

import static com.view.EntranceItemView.CLICK_ENTRANCE;

public class MainFragment extends BaseSectionFragment {

    private int role;//管理员还是用户


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected SectionViewFactroy getViewFactory() {
        return new SectionViewFactroy(getActivity()) {
            @Override
            protected SectionItemView createImpl(final LayoutInflater inflater, int section) {
                SectionItemView sectionItemView = null;

                switch (section) {
                    case ENTRANCE: {
                        sectionItemView = EntranceItemView.CREATOR.create(inflater, mListView);
                        sectionItemView.setSectionListener(new SectionItemView.SectionListener<EntranceItem>() {
                            @Override
                            public void onClickListener(int flag, EntranceItem model) {
                                if (flag == CLICK_ENTRANCE) {
                                    ToastUtil.showToast(getActivity(), model.getName());
                                    //testNet("63.223.108.42");
                                }
                                switch (model.getName()){
                                    case "校园地图":
                                        Intent intent1 = new Intent();
                                        intent1.setClass(getActivity(), MapActivity.class);
                                        startActivity(intent1);
                                        MainFragment.this.onDestroy();
                                        break;
                                    case "教室查询":
                                        Intent intent2 = new Intent(getActivity(), CRQueryActivity.class);
                                        startActivity(intent2);
                                        MainFragment.this.onDestroy();
                                        break;
                                    case "教室预约":
                                        Intent intent3 = new Intent(getActivity(), CRReserveActivity.class);
                                        startActivity(intent3);
                                        MainFragment.this.onDestroy();
                                        break;
                                    case "教室推荐":

                                        break;
                                    case "离线地图":
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
                        break;
                    }
                    default:
                        break;
                }

                return sectionItemView;
            }

            @Override
            protected int getSpan(int section) {
                return section & SPAN_MASK;
            }
        };
    }

    @Override
    protected void updateContent() {
        List<Section> data = new ArrayList<>();
        data.add(new EntranceItem("校园地图"));
        data.add(new EntranceItem("教室查询"));
        data.add(new EntranceItem("教室预约"));
        data.add(new EntranceItem("教室推荐"));
        if(role == Constant.admin){

            data.add(new EntranceItem("预约请求"));
        }
        mAdapter.setItems(data);
    }


}
