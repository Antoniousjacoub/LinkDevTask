package com.example.antonio.linkdevtask;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.example.antonio.linkdevtask.dagger.component.DaggerNetComponent;
import com.example.antonio.linkdevtask.dagger.component.NetComponent;
import com.example.antonio.linkdevtask.dagger.module.AppModule;
import com.example.antonio.linkdevtask.dagger.module.NetModule;
import com.example.antonio.linkdevtask.utils.STATICS;

import retrofit2.Retrofit;

/**
 * Created by antonio on 1/16/19.
 */

public class App extends Application {
    Retrofit retrofit;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private NetComponent mNetComponent;

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(STATICS.BASE_URL, getApplicationContext()))
                .build();
        retrofit = mNetComponent.getRetrofit();
    }



}