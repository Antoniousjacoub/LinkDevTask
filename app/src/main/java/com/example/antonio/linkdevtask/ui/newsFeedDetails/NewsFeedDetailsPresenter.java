package com.example.antonio.linkdevtask.ui.newsFeedDetails;

import android.os.Bundle;

import com.example.antonio.linkdevtask.dataModel.Article;

import static com.example.antonio.linkdevtask.ui.newsFeedDetails.NewsFeedDetailsActivity.ARTICLE_KEY;

public class NewsFeedDetailsPresenter {

    private NewsFeedDetailsView newsFeedDetailsView;

    public NewsFeedDetailsPresenter(NewsFeedDetailsView newsFeedDetailsView) {
        this.newsFeedDetailsView = newsFeedDetailsView;
    }

    public void handleNewsFeedDetailsData(Bundle bundle) {
        if (bundle == null)
            return;

        Article article = bundle.getParcelable(ARTICLE_KEY);
        newsFeedDetailsView.onNewsFeedDetailsData(article);

    }


}
