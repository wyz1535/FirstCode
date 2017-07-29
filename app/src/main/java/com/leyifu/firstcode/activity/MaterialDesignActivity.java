package com.leyifu.firstcode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.leyifu.firstcode.R;

public class MaterialDesignActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.it_information:
                Toast.makeText(this,"this is a information",Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_delete:
                Toast.makeText(this,"this is a delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_setting:
                Toast.makeText(this,"this is a setting",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
