package com.example.antonio.linkdevtask.ui.newsFeedDetails;

import android.content.Context;
import android.os.Bundle;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.dataModel.Article;
import com.example.antonio.linkdevtask.utils.Utils;

import static com.example.antonio.linkdevtask.ui.newsFeedDetails.NewsDetailsFragment.ARTICLE_KEY;


public class NewsFeedDetailsPresenter {

    private NewsFeedDetailsView newsFeedDetailsView;

    NewsFeedDetailsPresenter(NewsFeedDetailsView newsFeedDetailsView) {
        this.newsFeedDetailsView = newsFeedDetailsView;
    }

    void handleNewsFeedDetailsData(Context context,Bundle bundle) {
        if (context==null)return;
        if (bundle == null) {
            Utils.showMessage(context,context.getString(R.string.somthing_went_wrong));
            return;
        }
        Article article = bundle.getParcelable(ARTICLE_KEY);
        newsFeedDetailsView.onNewsFeedDetailsData(article);

    }


}
