package com.example.antonio.linkdevtask.ui.main;

import android.os.Bundle;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.ui.base.BaseActivityForDrawer;

import butterknife.ButterKnife;

public class HomeNewsFeedActivity extends BaseActivityForDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbarTitle(getString(R.string.link_development));
        if (savedInstanceState == null)
            addFragment(R.id.container_home, HomeNewsFeedFragment.getInstance(), HomeNewsFeedFragment.TAG);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
