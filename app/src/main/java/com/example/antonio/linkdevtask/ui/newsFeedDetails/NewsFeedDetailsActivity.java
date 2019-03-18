package com.example.antonio.linkdevtask.ui.newsFeedDetails;

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
import com.example.antonio.linkdevtask.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsFeedDetailsActivity extends AppCompatActivity implements NewsFeedDetailsView {

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
    private String urlArticle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_details);
        ButterKnife.bind(this);
        NewsFeedDetailsPresenter newsFeedDetailsPresenter = new NewsFeedDetailsPresenter(this);
        newsFeedDetailsPresenter.handleNewsFeedDetailsData(getIntent().getExtras());
    }


    private void onSetDataOnView(Article article) {
        if (article == null)
            return;

        urlArticle = article.getUrl();
        tvAuthor.setText(Utils.validString(article.getAuthor()));
        tvNewsFeedTitle.setText(Utils.validString(article.getTitle()));
        tvNewsDetailsDesc.setText(Utils.validString(article.getDescription()));
        tvDatePublished.setText(Utils.parseDate(article.getPublishedAt()));
        Utils.loadImageWithGlide(this, imgNewsFeedDetails, article.getUrlToImage());


    }

    @OnClick({R.id.img_back, R.id.img_search, R.id.btn_open_website})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_search:
                break;
            case R.id.btn_open_website:
                if (urlArticle != null)
                    Utils.openWebsiteOnBrowser(this, urlArticle);
                break;
        }
    }


    @Override
    public void onNewsFeedDetailsData(Article article) {
        onSetDataOnView(article);
    }
}
