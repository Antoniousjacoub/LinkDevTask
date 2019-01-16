package com.example.antonio.linkdevtask.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.adapters.CustomDrawerAdapter;
import com.example.antonio.linkdevtask.dataModel.DrawerItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseActivityForDrawer extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.img_open_drawer)
    ImageView imgOpenDrawer;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.flContent)
    public FrameLayout flContent;
    @BindView(R.id.menuList)
    ListView menuList;
    @BindView(R.id.nvView)
    NavigationView nvView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.load_view)
    public FrameLayout loadView;
    private Intent intent;
    private ArrayList<DrawerItem> dataListOFMenuItems;
    int mSelectedItem = 0;
    private CustomDrawerAdapter customDrawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_for_drawer);
    }

    @Override
    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base_for_drawer, null);
        FrameLayout activityContainer = fullView.findViewById(R.id.flContent);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);


    }

    public void initToolbarTitle(String title) {
        if (title == null)
            title = "";

        tvToolbarTitle.setText(title);


    }

    public void setupDrawerContent() {

        dataListOFMenuItems = new ArrayList<DrawerItem>();
        dataListOFMenuItems.add(new DrawerItem(getString(R.string.str_explore), R.drawable.ic_explore));
        dataListOFMenuItems.add(new DrawerItem(getString(R.string.str_menu_live_chat), R.drawable.ic_live));
        dataListOFMenuItems.add(new DrawerItem(getString(R.string.str_menu_gallery), R.drawable.ic_gallery));
        dataListOFMenuItems.add(new DrawerItem(getString(R.string.str_menu_wish_list), R.drawable.ic_wishlist));
        dataListOFMenuItems.add(new DrawerItem(getString(R.string.str_menu_e_magazine), R.drawable.ic_e_magazine));

        LayoutInflater myinflater = getLayoutInflater();
        ViewGroup myHeader = (ViewGroup) myinflater.inflate(R.layout.nav_header, menuList, false);
        menuList.addHeaderView(myHeader, null, false);
        customDrawerAdapter = new CustomDrawerAdapter(this, R.layout.drawer_list_item,
                dataListOFMenuItems, mSelectedItem);

        menuList.setAdapter(customDrawerAdapter);
        menuList.setOnItemClickListener(this);

    }

    @OnClick({R.id.img_open_drawer, R.id.img_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_open_drawer:
                drawerLayout.openDrawer(Gravity.START);
                break;
            case R.id.img_search:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        drawerLayout.closeDrawers();
        mSelectedItem = i;
        customDrawerAdapter.notifyDataSetChanged();
        switch (i) {
            case 1:

                break;

            case 2:


                break;

            case 3:


                break;

            case 4:
                break;


        }


    }

    private void setIndicatorColor(int position) {
        if (dataListOFMenuItems == null)
            return;

        for (int i = 0; i < dataListOFMenuItems.size(); i++) {
            if (i == position) {

            } else {

            }

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}
