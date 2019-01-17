package com.example.antonio.linkdevtask.ui.newsFeedDetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.dataModel.Article;
import com.example.antonio.linkdevtask.utils.Helpers;
import com.example.antonio.linkdevtask.utils.Settings;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsFeedDetailsActivity extends AppCompatActivity {

    public static String ARTICLE_KEY = "ARTICLE_KEY";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.img_news_feed_details)
    ImageView imgNewsFeedDetails;
    @BindView(R.id.tv_date_published)
    TextView tvDatePublished;
    @BindView(R.id.relativelayout)
    RelativeLayout relativelayout;
    @BindView(R.id.tv_news_feed_title)
    TextView tvNewsFeedTitle;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.ln_container_author)
    LinearLayout lnContainerAuthor;
    @BindView(R.id.tv_news_details_desc)
    TextView tvNewsDetailsDesc;
    @BindView(R.id.btn_open_website)
    Button btnOpenWebsite;
    private Article articleModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_details);
        ButterKnife.bind(this);

        if (getIntent() != null && getIntent().getExtras() != null) {
            articleModel = getIntent().getExtras().getParcelable(ARTICLE_KEY);
            onSetDataOnView(articleModel);
        }


    }


    private void onSetDataOnView(Article article) {
        if (article == null)
            return;

        tvAuthor.setText(Helpers.validString(article.getAuthor()));
        tvNewsFeedTitle.setText(Helpers.validString(article.getTitle()));
        tvNewsDetailsDesc.setText(Helpers.validString(article.getDescription()));
        tvDatePublished.setText(Helpers.parseDate(article.getPublishedAt()));
        Settings.loadImageWithGlide(this, imgNewsFeedDetails, article.getUrlToImage());


    }

    @OnClick({R.id.img_back, R.id.img_search, R.id.btn_open_website})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                break;
            case R.id.img_search:
                break;
            case R.id.btn_open_website:
                openWebsiteOnBrowser();
                break;
        }
    }

    private void openWebsiteOnBrowser() {
        if (articleModel == null || articleModel.getUrl() == null || articleModel.getUrl().equals(""))
            return;//we don't need to open website if this check is true

        Uri webpage = Uri.parse(articleModel.getUrl());

        if (!articleModel.getUrl().startsWith("http://") && !articleModel.getUrl().startsWith("https://")) {
            webpage = Uri.parse("http://" + articleModel.getUrl());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
