package com.leyifu.firstcode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.leyifu.firstcode.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private Button btn_simple;
    private Button btn_down_load;
    private Button btn_baid_location;
    private Button btn_material_design;
    private Button btn_drawerLayout;
    private Button btn_collapsing_tool_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        btn_simple = ((Button) findViewById(R.id.btn_simple));
        btn_down_load = ((Button) findViewById(R.id.btn_down_load));
        btn_baid_location = ((Button) findViewById(R.id.btn_down_load));
        btn_material_design = ((Button) findViewById(R.id.btn_material_design));
        btn_drawerLayout = ((Button) findViewById(R.id.btn_drawerLayout));
        btn_collapsing_tool_bar = ((Button) findViewById(R.id.btn_collapsing_tool_bar));

        btn_simple.setOnClickListener(this);
        btn_down_load.setOnClickListener(this);
        btn_baid_location.setOnClickListener(this);
        btn_material_design.setOnClickListener(this);
        btn_drawerLayout.setOnClickListener(this);
        btn_collapsing_tool_bar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simple:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.btn_down_load:
                startActivity(new Intent(this, DownLoadActivity.class));
                break;
            case R.id.btn_baid_location:
                startActivity(new Intent(this, DownLoadActivity.class));
                break;
            case R.id.btn_material_design:
                startActivity(new Intent(this, MaterialDesignActivity.class));
                break;
            case R.id.btn_drawerLayout:
                startActivity(new Intent(this, DrawerLayoutActivity.class));
                break;
            case R.id.btn_collapsing_tool_bar:
                startActivity(new Intent(this, CollapsingToolBarActivity.class));
                break;
        }
    }
}
