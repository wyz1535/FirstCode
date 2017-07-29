package com.leyifu.firstcode.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.leyifu.firstcode.R;
import com.leyifu.firstcode.view.MyToggle;

public class CollapsingToolBarActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = CollapsingToolBarActivity.class.getSimpleName();
    private MyToggle my_toggle_top;
    private boolean isToggleon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_tool_bar);

        my_toggle_top = ((MyToggle) findViewById(R.id.my_toggle_top));

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isToggleon = sharedPreferences.getBoolean("isToggleon", false);
        my_toggle_top.setToggleOn(isToggleon);
        Log.e(TAG, "isToggleon: " + isToggleon);

        my_toggle_top.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "001", Toast.LENGTH_SHORT).show();
        if (my_toggle_top.isToggleon()) {
            my_toggle_top.setToggleOn(false);
        } else {
            my_toggle_top.setToggleOn(true);
        }
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putBoolean("isToggleon",my_toggle_top.isToggleon() );
        editor.apply();
    }
}
