package com.example.antonio.linkdevtask.ui.base;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.adapters.CustomDrawerAdapter;
import com.example.antonio.linkdevtask.dataModel.DrawerItem;
import com.example.antonio.linkdevtask.utils.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseActivityForDrawer extends AppCompatActivity implements OnItemSideMenuClicked {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.flContent)
    public FrameLayout flContent;
    @BindView(R.id.menuList)
    RecyclerView rvMenuList;
    @BindView(R.id.nvView)
    NavigationView nvView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.load_view)
    public FrameLayout loadView;
    private ArrayList<DrawerItem> dataListOFMenuItems;
    private CustomDrawerAdapter customDrawerAdapter;

    public static int positionSelectedSideMenu;//make the 0 position the default selected

    @Override
    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base_for_drawer, null);
        FrameLayout activityContainer = fullView.findViewById(R.id.flContent);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    public void initToolbarTitle(String title) {
        if (title == null)
            title = "";

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
        toolbar.inflateMenu(R.menu.main_menu);
        setupDrawerContent();
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

    public void setupDrawerContent() {

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        dataListOFMenuItems = new ArrayList<DrawerItem>();
        DrawerItem item_1 = new DrawerItem();
        item_1.setItemName(getString(R.string.str_menu_explore));
        item_1.setImgResID(R.drawable.ic_explore);
        dataListOFMenuItems.add(item_1);

        DrawerItem item_2 = new DrawerItem();
        item_2.setItemName(getString(R.string.str_menu_live_chat));
        item_2.setImgResID(R.drawable.ic_live);
        dataListOFMenuItems.add(item_2);
        DrawerItem item_3 = new DrawerItem();
        item_3.setItemName(getString(R.string.str_menu_gallery));
        item_3.setImgResID(R.drawable.ic_gallery);
        dataListOFMenuItems.add(item_3);
        DrawerItem item_4 = new DrawerItem();
        item_4.setItemName(getString(R.string.str_menu_wish_list));
        item_4.setImgResID(R.drawable.ic_wishlist);
        dataListOFMenuItems.add(item_4);
        DrawerItem item_5 = new DrawerItem();
        item_5.setItemName(getString(R.string.str_menu_e_magazine));
        item_5.setImgResID(R.drawable.ic_e_magazine);
        dataListOFMenuItems.add(item_5);

        customDrawerAdapter = new CustomDrawerAdapter(this, dataListOFMenuItems, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMenuList.setLayoutManager(layoutManager);
        rvMenuList.setAdapter(customDrawerAdapter);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    @Override
    public void onItemSideMenuClicked(int position) {
        drawerLayout.closeDrawers();
        positionSelectedSideMenu = position ;
        customDrawerAdapter.notifyDataSetChanged();

        switch (position) {
            case 0:

                Utils.showMessage(this, getString(R.string.str_menu_explore));
                break;

            case 1:
                Utils.showMessage(this, getString(R.string.str_menu_live_chat));
                break;

            case 2:
                Utils.showMessage(this, getString(R.string.str_menu_gallery));
                break;

            case 3:
                Utils.showMessage(this, getString(R.string.str_menu_wish_list));
                break;
            case 4:
                Utils.showMessage(this, getString(R.string.str_menu_e_magazine));

                break;


        }
    }
}
