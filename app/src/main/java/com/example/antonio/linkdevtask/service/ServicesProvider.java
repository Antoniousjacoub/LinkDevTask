package com.example.antonio.linkdevtask.service;

import android.content.Context;

import retrofit2.Retrofit;

/**
 * Created by antonio on 1/16/19.
 */

public class ServicesProvider {

    public ServicesInterface getApiService(Retrofit retrofit) {
        return retrofit.create(ServicesInterface.class);
    }
}
