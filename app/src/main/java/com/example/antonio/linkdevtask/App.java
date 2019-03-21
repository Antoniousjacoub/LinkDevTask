package com.example.antonio.linkdevtask;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.example.antonio.linkdevtask.dagger.component.DaggerNetworkingComponent;
import com.example.antonio.linkdevtask.dagger.component.NetworkingComponent;
import com.example.antonio.linkdevtask.dagger.module.NetworkingModule;
import com.example.antonio.linkdevtask.utils.Constants;

/**
 * Created by antonio on 1/16/19.
 */

public class App extends Application {
    private  NetworkingComponent mNetworkingComponent;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public  NetworkingComponent getNetComponent() {
        return mNetworkingComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNetworkingComponent = DaggerNetworkingComponent.builder()
                .networkingModule(new NetworkingModule(Constants.BASE_URL))
                .build();

    }


}