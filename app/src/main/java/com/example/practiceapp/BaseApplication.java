package com.example.simpleapp;

import android.app.Application;

import com.example.simpleapp.di.components.AppComponent;
import com.example.simpleapp.di.components.DaggerAppComponent;

public class BaseApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
