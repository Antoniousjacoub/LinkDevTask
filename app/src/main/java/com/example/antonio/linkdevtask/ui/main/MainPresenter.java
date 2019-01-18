package com.example.antonio.linkdevtask.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.dataModel.NewsFeedResponse;
import com.example.antonio.linkdevtask.service.ServicesInterface;
import com.example.antonio.linkdevtask.utils.STATICS;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by antonio on 1/16/19.
 */

class MainPresenter {


    private Context context;
    private Retrofit retrofit;
    private MainViewInterface mainViewInterface;

    MainPresenter(Context context,Retrofit retrofit, MainViewInterface mainViewInterface) {
        this.retrofit = retrofit;
        this.context=context;
        this.mainViewInterface = mainViewInterface;
    }

    void getNewsFeed() {
        if (retrofit == null || mainViewInterface == null)
            return;//early
        mainViewInterface.showLoadingAnimation();
        Call<NewsFeedResponse> homeData = retrofit.create(ServicesInterface.class).getNewsFeed(STATICS.SOURCE,
                STATICS.API_KEY);
        homeData.enqueue(new Callback<NewsFeedResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsFeedResponse> call, @NonNull final Response<NewsFeedResponse> response) {
                mainViewInterface.hideLoadingAnimation();
                if (response.isSuccessful()) {
                    mainViewInterface.onNewsFeedLoaded(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<NewsFeedResponse> call, @NonNull Throwable t) {
                mainViewInterface.onError(t);
                processError(t);
                Log.e("onFailure", t.getMessage() + "");
                Log.e("onFailure", t.getLocalizedMessage() + "");

            }
        });
    }

    private void processError(Throwable throwable) {
        if (mainViewInterface==null||throwable==null||context==null)
            return;
        mainViewInterface.hideLoadingAnimation();
        if (throwable instanceof HttpException) {
            mainViewInterface.showErrorMessage(((HttpException) throwable).message());
        } else if (throwable instanceof IOException) {
            mainViewInterface.showErrorMessage(context.getString(R.string.error_network));
        } else {
            mainViewInterface.showErrorMessage(context.getString(R.string.error_communicating_with_server));
        }
    }
}

