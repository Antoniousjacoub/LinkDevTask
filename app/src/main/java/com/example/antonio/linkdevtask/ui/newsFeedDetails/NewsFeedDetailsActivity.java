package com.example.antonio.linkdevtask.ui.newsFeedDetails;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFeedDetailsActivity extends BaseActivity {


    @BindView(R.id.container_home)
    FrameLayout containerHome;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_details);
        ButterKnife.bind(this);
        handleToolbar();
        addFragment(R.id.container_home, NewsDetailsFragment.getInstance(getIntent().getExtras()), NewsDetailsFragment.TAG);
    }

    private void handleToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.link_development));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        toolbar.inflateMenu(R.menu.main_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
