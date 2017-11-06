package com.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.crmapp.R;

/**
 * Created by zjr on 2017/11/6.
 * 自定义的view,作为整个app的actionbar
 */

public class ActionBarView extends LinearLayout {


    private ImageButton back;
    private ImageButton more;
    private TextView title;

    public ActionBarView(Context context) {
        super(context);
    }

    public ActionBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = View.inflate(context, R.layout.layout_action_bar,null);
        back = (ImageButton) view.findViewById(R.id.back);
        more = (ImageButton) view.findViewById(R.id.more);
        title = (TextView) view.findViewById(R.id.title);

        addView(view);
    }

    public void setOnBackClick(OnClickListener listener){
        back.setOnClickListener(listener);
    }

    public void setOnMoreClick(OnClickListener listener){
        more.setOnClickListener(listener);
    }

    public void setTitle(String s){
        title.setText(s);
    }
}
