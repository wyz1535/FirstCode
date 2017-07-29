package com.leyifu.firstcode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leyifu.firstcode.R;

public class FruitActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView image_view;
    private TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        String fruit_name = intent.getStringExtra("fruit_name");
        int fruit_image_id = intent.getIntExtra("fruit_image_id", 0);
        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        collapsingToolbarLayout = ((CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout));
        image_view = ((ImageView) findViewById(R.id.image_view));
        text_view = ((TextView) findViewById(R.id.text_view));

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbarLayout.setTitle(fruit_name);
        Glide.with(this).load(fruit_image_id).into(image_view);
        String fruitcontent = genrateFruitcontent(fruit_name);
        text_view.setText(fruitcontent);
    }

    private String genrateFruitcontent(String fruit_name) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i=0;i<500;i++) {
            fruitContent.append(fruit_name);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
