package com.example.antonio.linkdevtask.ui.newsFeedDetails;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.fragments.NewsDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFeedDetailsActivity extends AppCompatActivity implements NewsDetailsFragment.OnFragmentInteractionListener {


    @BindView(R.id.container_home)
    FrameLayout containerHome;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.normal_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_details);
        ButterKnife.bind(this);
        addFragment(R.id.container_home, new NewsDetailsFragment().getInstance(getIntent().getExtras()), NewsDetailsFragment.TAG);
        handleToolbar();
    }

    private void handleToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
