package com.leyifu.firstcode;

import android.app.Application;
import android.content.Context;

/**
 * Created by xingxing on 2017/7/7.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
