package com.leyifu.firstcode.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leyifu.firstcode.R;

/**
 * Created by xingxing on 2017/7/29.
 */
public class MyToggle extends LinearLayout {

    private TextView tv_toggle;
    private ImageView iv_toggle;
    private RelativeLayout rl_toggle;
    private boolean isToggleon;

    public MyToggle(Context context) {
        this(context, null);
    }

    public MyToggle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        View view = View.inflate(context, R.layout.my_toggle, null);
        View view = LayoutInflater.from(context).inflate(R.layout.my_toggle, null);
        addView(view);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MyToggle);
        String title = attributes.getString(R.styleable.MyToggle_title);
        String type = attributes.getString(R.styleable.MyToggle_type);
        attributes.recycle();

        tv_toggle = ((TextView) findViewById(R.id.tv_toggle));
        iv_toggle = ((ImageView) findViewById(R.id.iv_toggle));
        rl_toggle = ((RelativeLayout) findViewById(R.id.rl_toggle));
        tv_toggle.setText(title);

        if ("top".equals(type)) {
            setBackgroundResource(R.drawable.top_toggle_selector);
        } else if ("mid".equals(type)) {
            setBackgroundResource(R.drawable.mid_toggle_selector);
        } else if ("button".equals(type)) {
            setBackgroundResource(R.drawable.button_toggle_selector);
        }

    }

    public void setToggleOn(boolean isToggleOn) {
        this.isToggleon = isToggleOn;
        if (isToggleOn) {
            iv_toggle.setImageResource(R.drawable.on);
        } else {
            iv_toggle.setImageResource(R.drawable.off);
        }
    }

    public boolean isToggleon() {
        return isToggleon;
    }

    public void toggle() {
//        if (isToggleon) {
//            setToggleOn(false);
//        } else {
//            setToggleOn(true);
//        }

        setToggleOn(!isToggleon);
    }
}
