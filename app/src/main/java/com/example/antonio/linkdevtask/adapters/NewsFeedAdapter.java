package com.example.antonio.linkdevtask.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.dataModel.Article;
import com.example.antonio.linkdevtask.dataModel.NewsFeedResponse;
import com.example.antonio.linkdevtask.ui.newsFeedDetails.NewsFeedDetailsActivity;
import com.example.antonio.linkdevtask.utils.Helpers;
import com.example.antonio.linkdevtask.utils.Settings;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antonio on 1/16/19.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> {

    private NewsFeedResponse mData;
    private LayoutInflater mInflater;
    private Context context;

    // data is passed into the constructor
    public NewsFeedAdapter(Context context, NewsFeedResponse data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_news_feed, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article=mData.getArticles().get(position);

        holder.tvAuthor.setText(Helpers.validString(article.getAuthor()));
        holder.tvNewsFeedTitle.setText(Helpers.validString(article.getTitle()));
        holder.tvPublishDate.setText(Helpers.parseDate(article.getPublishedAt()));
        Settings.loadImageWithGlide(context,holder.imgNewsFeed,article.getUrlToImage());
        holder.cardView.setOnClickListener(view -> {
            Intent intent =new Intent(context, NewsFeedDetailsActivity.class);
            intent.putExtra(NewsFeedDetailsActivity.ARTICLE_KEY,article);
            context.startActivity(intent);
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.getArticles().size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_news_feed)
        ImageView imgNewsFeed;
        @BindView(R.id.tv_news_feed_title)
        TextView tvNewsFeedTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_publish_date)
        TextView tvPublishDate;
        @BindView(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
