package com.example.antonio.linkdevtask.ui.newsFeedDetails;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.antonio.linkdevtask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFeedDetailsActivity extends AppCompatActivity implements NewsDetailsFragment.OnFragmentInteractionListener {


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
        addFragment(R.id.container_home, new NewsDetailsFragment().getInstance(getIntent().getExtras()), NewsDetailsFragment.TAG);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
