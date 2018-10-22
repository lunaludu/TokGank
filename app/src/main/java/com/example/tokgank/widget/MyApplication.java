package com.example.tokgank.widget;

import android.app.Application;
import android.content.Context;

/**
 * Created by Hugh on 2018/5/30.
 */

public class MyApplication extends Application{
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext=getApplicationContext();
    }

    public static Context getContext(){
        return sContext;
    }
}
