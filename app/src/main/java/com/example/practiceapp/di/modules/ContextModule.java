package com.example.simpleapp.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Binds
    abstract Context bindContext(Application application);

}
