package com.leyifu.firstcode.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.leyifu.firstcode.R;
import com.leyifu.firstcode.adapter.FruitAdapter;
import com.leyifu.firstcode.bean.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawerLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView navigation_view;
    private FloatingActionButton floating_action_button;
    private RecyclerView recycle_view;

    Fruit[] fruits = new Fruit[]{new Fruit("apple", R.drawable.header)
            , new Fruit("apple01", R.drawable.header)
            , new Fruit("apple02", R.drawable.header)
            , new Fruit("apple03", R.drawable.header)
            , new Fruit("apple04", R.drawable.header)
            , new Fruit("apple05", R.drawable.header)
            , new Fruit("apple06", R.drawable.header)
            , new Fruit("apple07", R.drawable.header)
            , new Fruit("apple08", R.drawable.header)
            , new Fruit("apple09", R.drawable.header)
            , new Fruit("apple10", R.drawable.header)
            , new Fruit("apple11", R.drawable.header)};

    private List<Fruit> fruitList = new ArrayList<>();
    private SwipeRefreshLayout swipe_refresh;
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
        drawer_layout = ((DrawerLayout) findViewById(R.id.drawer_layout));
        navigation_view = ((NavigationView) findViewById(R.id.navigation_view));
        floating_action_button = ((FloatingActionButton) findViewById(R.id.floating_action_button));
        recycle_view = ((RecyclerView) findViewById(R.id.recycle_view));
        swipe_refresh = ((SwipeRefreshLayout) findViewById(R.id.swipe_refresh));

        //下拉刷新
        swipeRefresh();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.information);
        }


        GridLayoutManager gridLayout = new GridLayoutManager(this, 2);
        recycle_view.setLayoutManager(gridLayout);
        adapter = new FruitAdapter(fruitList);
        recycle_view.setAdapter(adapter);

        floating_action_button.setOnClickListener(this);

        initFruit();
        initNavigation();

    }

    private void swipeRefresh() {
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fruitRefresh();
            }
        });
    }

    private void fruitRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruit();
                        adapter.notifyDataSetChanged();
                        swipe_refresh.setRefreshing(false);
                        Toast.makeText(DrawerLayoutActivity.this,"this is refresh",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    private void initNavigation() {
        navigation_view.setCheckedItem(R.id.nav_01);

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_01:
                        Toast.makeText(DrawerLayoutActivity.this, "this is a nav_01", Toast.LENGTH_SHORT).show();
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_02:
                        Toast.makeText(DrawerLayoutActivity.this, "nav_02", Toast.LENGTH_SHORT).show();
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_03:
                        Toast.makeText(DrawerLayoutActivity.this, "nav_03", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_04:
                        Toast.makeText(DrawerLayoutActivity.this, "nav_04", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_05:
                        Toast.makeText(DrawerLayoutActivity.this, "nav_05", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void initFruit() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
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
                Toast.makeText(this, "this is a information", Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_delete:
                Toast.makeText(this, "this is a delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.it_setting:
                Toast.makeText(this, "this is a setting", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button:
                Snackbar.make(v, "this is snackBar", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DrawerLayoutActivity.this, "this is a floating_action_button", Toast.LENGTH_SHORT).show();
                        drawer_layout.openDrawer(GravityCompat.START);

                    }
                }).show();
                break;
        }
    }
}
