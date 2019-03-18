package com.example.antonio.linkdevtask.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.dataModel.Article;
import com.example.antonio.linkdevtask.ui.newsFeedDetails.NewsFeedDetailsPresenter;
import com.example.antonio.linkdevtask.ui.newsFeedDetails.NewsFeedDetailsView;
import com.example.antonio.linkdevtask.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NewsDetailsFragment extends Fragment implements NewsFeedDetailsView {

    public static final String TAG = "NewsDetailsFragmentTag";
    public static String ARTICLE_KEY = "ARTICLE_KEY";
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
    @BindView(R.id.card_view)
    CardView cardView;
    Unbinder unbinder;
    private String urlArticle;


    private OnFragmentInteractionListener mListener;
    private NewsDetailsFragment fragment;

    public NewsDetailsFragment() {

    }


    public NewsDetailsFragment getInstance(Bundle bundle) {
        if (fragment == null) {
            fragment = new NewsDetailsFragment();
            fragment.setArguments(bundle);
            return fragment;
        } else {
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_news_details, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        NewsFeedDetailsPresenter newsFeedDetailsPresenter = new NewsFeedDetailsPresenter(this);
        newsFeedDetailsPresenter.handleNewsFeedDetailsData(getArguments());
        return rootView;
    }


    private void onSetDataOnView(Article article) {
        if (article == null)
            return;

        urlArticle = article.getUrl();
        tvAuthor.setText(Utils.validString(article.getAuthor()));
        tvNewsFeedTitle.setText(Utils.validString(article.getTitle()));
        tvNewsDetailsDesc.setText(Utils.validString(article.getDescription()));
        tvDatePublished.setText(Utils.parseDate(article.getPublishedAt()));
        Utils.loadImageWithGlide(getContext(), imgNewsFeedDetails, article.getUrlToImage());


    }

    @OnClick({R.id.img_search, R.id.btn_open_website})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.img_search:
                break;
            case R.id.btn_open_website:
                if (urlArticle != null)
                    Utils.openWebsiteOnBrowser(getActivity(), urlArticle);
                break;
        }
    }


    @Override
    public void onNewsFeedDetailsData(Article article) {
        onSetDataOnView(article);
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
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
