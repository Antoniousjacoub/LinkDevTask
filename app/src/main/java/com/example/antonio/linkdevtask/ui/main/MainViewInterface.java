package com.example.antonio.linkdevtask.ui.main;

import com.example.antonio.linkdevtask.dataModel.NewsFeedResponse;

/**
 * Created by antonio on 1/16/19.
 */

public interface MainViewInterface {
    void onNewsFeedLoaded(NewsFeedResponse newsFeedResponse);
    void hideLoadingAnimation();
    void showErrorMessage(String message);
    void showLoadingAnimation();
}
