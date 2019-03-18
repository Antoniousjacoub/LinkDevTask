package com.example.antonio.linkdevtask.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.adapters.NewsFeedAdapter;
import com.example.antonio.linkdevtask.dataModel.NewsFeedResponse;
import com.example.antonio.linkdevtask.ui.base.BaseActivityForDrawer;
import com.example.antonio.linkdevtask.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivityForDrawer implements MainViewInterface {

    @BindView(R.id.rv_news_feed)
    RecyclerView rvNewsFeed;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupDrawerContent();
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        handleNewsRequest();


    }

    private void handleNewsRequest() {
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.getNewsFeed(false);
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> {
        if (mainPresenter != null) {
            mainPresenter.getNewsFeed(true);
        }
    };

    @Override
    public void onNewsFeedLoaded(NewsFeedResponse newsFeedResponse) {
        if (newsFeedResponse == null) {
            Utils.showMessage(this, getString(R.string.no_data_to_show));
            return;
        }

        rvNewsFeed.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(MainActivity.this, newsFeedResponse.getArticles());
        rvNewsFeed.setAdapter(newsFeedAdapter);

    }

    @Override
    public void hideLoadingAnimation() {
        loadView.setVisibility(View.GONE);
        flContent.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void showErrorMessage(String message) {
        Utils.showMessage(this, message);
    }

    @Override
    public void showLoadingAnimation() {
        loadView.setVisibility(View.VISIBLE);
        flContent.setVisibility(View.GONE);
    }


}
