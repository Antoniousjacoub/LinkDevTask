package com.example.antonio.linkdevtask.ui.base;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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
import com.example.antonio.linkdevtask.utils.Utils;

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
    RecyclerView menuList;
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

    public void initToolbarTitle(String title) {
        if (title == null)
            title = "";

        tvToolbarTitle.setText(title);


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

        // TODO: 3/18/2019 handle header
//        LayoutInflater myinflater = getLayoutInflater();
//        ViewGroup myHeader = (ViewGroup) myinflater.inflate(R.layout.nav_header, menuList, false);
//        menuList.addHeaderView(myHeader, null, false);
        customDrawerAdapter = new CustomDrawerAdapter(this, dataListOFMenuItems);

        menuList.setAdapter(customDrawerAdapter);

        //todo handle on click
//        menuList.setOnItemClickListener(this);

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
        positionSelectedSideMenu =i-1;
        customDrawerAdapter.notifyDataSetChanged();

        switch (i) {
            case 1:

                Utils.showMessage(this, getString(R.string.str_menu_explore));
                break;

            case 2:
                Utils.showMessage(this, getString(R.string.str_menu_live_chat));
                break;

            case 3:
                Utils.showMessage(this, getString(R.string.str_menu_gallery));
                break;

            case 4:
                Utils.showMessage(this, getString(R.string.str_menu_wish_list));
                break;
            case 5:
                Utils.showMessage(this,getString(R.string.str_menu_e_magazine));

                break;


        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}
