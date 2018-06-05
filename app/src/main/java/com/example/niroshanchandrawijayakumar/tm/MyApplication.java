package com.example.niroshanchandrawijayakumar.tm;

import android.app.Application;
import android.content.Context;

import com.example.niroshanchandrawijayakumar.tm.Dependencies.DependencyRegistry;

public class MyApplication extends Application {

    public static Context appContext ;

    DependencyRegistry dependencyRegistry;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        dependencyRegistry = DependencyRegistry.shared;
    }
}
