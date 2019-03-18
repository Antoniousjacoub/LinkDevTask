package com.example.antonio.linkdevtask.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.fragments.HomeNewsFeedFragment;
import com.example.antonio.linkdevtask.ui.base.BaseActivityForDrawer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivityForDrawer implements HomeNewsFeedFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbarTitle(getString(R.string.link_development));

        addFragment(R.id.container_home,new  HomeNewsFeedFragment().getInstance(),HomeNewsFeedFragment.TAG);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
