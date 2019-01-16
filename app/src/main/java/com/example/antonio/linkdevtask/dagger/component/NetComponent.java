package com.example.antonio.linkdevtask.dagger.component;

import com.example.antonio.linkdevtask.dagger.module.AppModule;
import com.example.antonio.linkdevtask.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by antonio on 1/16/19.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit getRetrofit() ;
}