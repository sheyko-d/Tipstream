package com.tipstream;

import android.app.Application;
import android.content.Context;

public class Tipstream extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getAppContext() {
        return sContext;
    }
}
