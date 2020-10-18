package com.example.simpleapp.di.modules;

import com.example.simpleapp.calls.UserCalls;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.simpleapp.utils.Constants.API_BASE_URL;

@Module(includes = ViewModelModule.class)
public abstract class NetworkModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static UserCalls provideUserServices(Retrofit retrofit) {
        return retrofit.create(UserCalls.class);
    }

}
