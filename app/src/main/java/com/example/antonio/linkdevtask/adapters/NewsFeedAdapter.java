package com.example.antonio.linkdevtask.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
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
import com.example.antonio.linkdevtask.dataModel.NewsFeedResponse;

import java.util.List;

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
        View view = mInflater.inflate(R.layout.activity_base_for_drawer, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


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
        @BindView(R.id.img_open_drawer)
        ImageView imgOpenDrawer;
        @BindView(R.id.img_search)
        ImageView imgSearch;
        @BindView(R.id.tv_toolbar_title)
        TextView tvToolbarTitle;
        @BindView(R.id.toolbar)
        Toolbar toolbar;
        @BindView(R.id.load_view)
        FrameLayout loadView;
        @BindView(R.id.flContent)
        FrameLayout flContent;
        @BindView(R.id.menuList)
        ListView menuList;
        @BindView(R.id.nvView)
        NavigationView nvView;
        @BindView(R.id.drawer_layout)
        DrawerLayout drawerLayout;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
