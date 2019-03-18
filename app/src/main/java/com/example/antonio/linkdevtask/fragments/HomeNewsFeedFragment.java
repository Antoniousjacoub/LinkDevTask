package com.example.antonio.linkdevtask.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.adapters.NewsFeedAdapter;
import com.example.antonio.linkdevtask.dataModel.NewsFeedResponse;
import com.example.antonio.linkdevtask.ui.main.MainPresenter;
import com.example.antonio.linkdevtask.ui.main.MainViewInterface;
import com.example.antonio.linkdevtask.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeNewsFeedFragment extends Fragment implements MainViewInterface {

    public static final String TAG = "HomeFragment";
    @BindView(R.id.rv_news_feed)
    RecyclerView rvNewsFeed;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.load_view)
    FrameLayout loadView;
    Unbinder unbinder;
    private MainPresenter mainPresenter;

    private OnFragmentInteractionListener mListener;
    private  HomeNewsFeedFragment fragment;

    public  HomeNewsFeedFragment getInstance() {
        if (fragment == null) {
            fragment = new HomeNewsFeedFragment();
            return fragment;
        } else {
            return fragment;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void handleNewsRequest() {
        if (getActivity() == null)
            return;
        mainPresenter = new MainPresenter(getActivity(), this);
        mainPresenter.getNewsFeed(false);
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> {
        if (mainPresenter != null) {
            mainPresenter.getNewsFeed(true);
        }
    };

    @Override
    public void onNewsFeedLoaded(NewsFeedResponse newsFeedResponse) {
        if (newsFeedResponse == null || getContext() == null) {
            Utils.showMessage(getContext(), getString(R.string.no_data_to_show));
            return;
        }

        rvNewsFeed.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(getContext(), newsFeedResponse.getArticles());
        rvNewsFeed.setAdapter(newsFeedAdapter);

    }

    @Override
    public void hideLoadingAnimation() {
        loadView.setVisibility(View.GONE);
        rvNewsFeed
                .setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void showErrorMessage(String message) {
        Utils.showMessage(getContext(), message);
    }

    @Override
    public void showLoadingAnimation() {
        loadView.setVisibility(View.VISIBLE);
        rvNewsFeed.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_news_feed, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        handleNewsRequest();
        return rootView;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder!=null) {
            unbinder.unbind();
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
