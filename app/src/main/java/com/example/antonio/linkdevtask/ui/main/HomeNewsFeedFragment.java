package com.example.antonio.linkdevtask.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.adapters.NewsFeedAdapter;
import com.example.antonio.linkdevtask.dataModel.Article;
import com.example.antonio.linkdevtask.dataModel.NewsFeedResponse;
import com.example.antonio.linkdevtask.ui.base.BaseFragment;
import com.example.antonio.linkdevtask.ui.newsFeedDetails.NewsDetailsFragment;
import com.example.antonio.linkdevtask.ui.newsFeedDetails.NewsFeedDetailsActivity;
import com.example.antonio.linkdevtask.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeNewsFeedFragment extends BaseFragment implements HomeNewsViewInterface, NewsFeedAdapter.OnItemNewsClicked {

    public static final String TAG = "HomeFragment";
    @BindView(R.id.rv_news_feed)
    RecyclerView rvNewsFeed;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.load_view)
    FrameLayout loadView;
    Unbinder unbinder;
    private HomeNewsFeedPresenter homeNewsFeedPresenter;
    private Context context;


    public static HomeNewsFeedFragment getInstance() {
        return new HomeNewsFeedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_news_feed, container, false);
        context = getActivity();
        unbinder = ButterKnife.bind(this, rootView);
        setListeners();
        handleNewsRequest();
        return rootView;
    }

    private void handleNewsRequest() {
        if (context == null)
            return;
        homeNewsFeedPresenter = new HomeNewsFeedPresenter(context, this);
        homeNewsFeedPresenter.getNewsFeed(false);
    }


    @Override
    public void onNewsFeedLoaded(NewsFeedResponse newsFeedResponse) {

        if (newsFeedResponse == null || context == null) {
            Utils.showMessage(context, getString(R.string.no_data_to_show));
            return;
        }

        rvNewsFeed.setLayoutManager(new LinearLayoutManager(context));
        NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(context, newsFeedResponse.getArticles(), this);
        rvNewsFeed.setAdapter(newsFeedAdapter);

    }

    @Override
    public void onHideRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void hideLoadingAnimation() {
        loadView.setVisibility(View.GONE);
        rvNewsFeed.setVisibility(View.VISIBLE);


    }

    @Override
    public void showErrorMessage(String message) {
        Utils.showMessage(context, message);
    }

    @Override
    public void showLoadingAnimation() {
        loadView.setVisibility(View.VISIBLE);
        rvNewsFeed.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (unbinder != null) {
//            unbinder.unbind();
//        }
    }

    @Override
    public void onItemNewsClicked(Article article, int position) {
        Intent intent = new Intent(getContext(), NewsFeedDetailsActivity.class);
        intent.putExtra(NewsDetailsFragment.ARTICLE_KEY, article);
        startActivity(intent);
    }

    @Override
    protected void setListeners() {
        SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> {
            if (homeNewsFeedPresenter != null) {
                homeNewsFeedPresenter.getNewsFeed(true);
            }
        };
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
