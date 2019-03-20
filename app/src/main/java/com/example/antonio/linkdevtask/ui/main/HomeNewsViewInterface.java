package com.example.antonio.linkdevtask.ui.main;

import com.example.antonio.linkdevtask.dataModel.NewsFeedResponse;
import com.example.antonio.linkdevtask.ui.base.BaseViewInterface;

/**
 * Created by antonio on 1/16/19.
 */

public interface HomeNewsViewInterface extends BaseViewInterface {
    void onNewsFeedLoaded(NewsFeedResponse newsFeedResponse);

    void onHideRefresh();

}
