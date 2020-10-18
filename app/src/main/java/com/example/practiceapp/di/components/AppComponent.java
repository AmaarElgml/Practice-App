package com.example.simpleapp.di.components;

import com.example.simpleapp.MainActivity;
import com.example.simpleapp.di.modules.ContextModule;
import com.example.simpleapp.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

}
