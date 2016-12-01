package com.myprojiect.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/11/23.
 */

public class retrofit extends Application {

    private static Context context;
    private static retrofit app;


    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        context=getApplicationContext();

    }



    public static Context getContext() {
        return context;
    }

    public static retrofit getInstance() {
        return app;
    }
}
