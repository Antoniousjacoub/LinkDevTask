package com.example.antonio.linkdevtask.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.antonio.linkdevtask.App;
import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.dataModel.NewsFeedResponse;
import com.example.antonio.linkdevtask.service.ServicesInterface;
import com.example.antonio.linkdevtask.utils.Constants;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by antonio on 1/16/19.
 */
public class HomeNewsFeedPresenter {
    @Inject
    ServicesInterface servicesInterface;
    private Context context;
    private HomeNewsViewInterface homeNewsViewInterface;

    HomeNewsFeedPresenter(Context context, HomeNewsViewInterface homeNewsViewInterface) {
//        this.servicesInterface = App.getNetComponent().getServicesInterface();
        ((App) context.getApplicationContext()).getNetComponent().inject(this);
        this.context = context;
        this.homeNewsViewInterface = homeNewsViewInterface;
    }

    void getNewsFeed(boolean isFromSwipeRefresh) {
        if (servicesInterface == null || homeNewsViewInterface == null)
            return;//early

        homeNewsViewInterface.showOrHideLoadingAnimation(!isFromSwipeRefresh);

        Call<NewsFeedResponse> homeData = servicesInterface.getNewsFeed(Constants.SOURCE, Constants.API_KEY);
        homeData.enqueue(new Callback<NewsFeedResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsFeedResponse> call, @NonNull final Response<NewsFeedResponse> response) {
                if (!isFromSwipeRefresh) {
                    homeNewsViewInterface.showOrHideLoadingAnimation(false);
                } else
                    homeNewsViewInterface.onHideRefresh();

                if (response.isSuccessful()) {
                    homeNewsViewInterface.onNewsFeedLoaded(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<NewsFeedResponse> call, @NonNull Throwable t) {
                processError(t);
                Log.e("onFailure", t.getMessage() + "");
                Log.e("onFailure", t.getLocalizedMessage() + "");

            }
        });
    }

    private void processError(Throwable throwable) {
        if (homeNewsViewInterface == null || throwable == null || context == null)
            return;
        homeNewsViewInterface.showOrHideLoadingAnimation(false);
        homeNewsViewInterface.onHideRefresh();
        if (throwable instanceof HttpException) {
            homeNewsViewInterface.showErrorMessage(((HttpException) throwable).message());
        } else if (throwable instanceof IOException) {
            homeNewsViewInterface.showErrorMessage(context.getString(R.string.error_network));
        } else {
            homeNewsViewInterface.showErrorMessage(context.getString(R.string.error_communicating_with_server));
        }
    }
}

