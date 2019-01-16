package com.example.antonio.linkdevtask.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by antonio on 1/16/19.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}
