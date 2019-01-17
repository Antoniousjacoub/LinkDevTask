package com.example.antonio.linkdevtask.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.antonio.linkdevtask.App;
import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.adapters.NewsFeedAdapter;
import com.example.antonio.linkdevtask.dataModel.NewsFeedResponse;
import com.example.antonio.linkdevtask.ui.base.BaseActivityForDrawer;
import com.example.antonio.linkdevtask.utils.Helpers;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class MainActivity extends BaseActivityForDrawer implements MainViewInterface {

    @BindView(R.id.rv_news_feed)
    RecyclerView rvNewsFeed;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private NewsFeedAdapter newsFeedAdapter;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupDrawerContent();
        Retrofit retrofit = ((App) getApplication()).getNetComponent().getRetrofit();
        mainPresenter = new MainPresenter(retrofit, this);
        mainPresenter.getNewsFeed();
        swipeRefreshLayout.setOnRefreshListener(() -> {
             mainPresenter = new MainPresenter(retrofit, this);
             mainPresenter.getNewsFeed();
        });
    }

    @Override
    public void onNewsFeedLoaded(NewsFeedResponse newsFeedResponse) {
        if (newsFeedResponse == null)
            return;

        rvNewsFeed.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        newsFeedAdapter = new NewsFeedAdapter(MainActivity.this, newsFeedResponse);
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
        Helpers.showMessage(this, message);
    }

    @Override
    public void showLoadingAnimation() {
        loadView.setVisibility(View.VISIBLE);
        flContent.setVisibility(View.GONE);
    }

    @Override
    public void onError(Throwable throwable) {
        swipeRefreshLayout.setRefreshing(false);

    }

}
