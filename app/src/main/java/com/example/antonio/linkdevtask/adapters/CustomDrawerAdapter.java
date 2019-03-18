package com.example.antonio.linkdevtask.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.dataModel.DrawerItem;
import com.example.antonio.linkdevtask.ui.base.BaseActivityForDrawer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antonio on 1/16/19.
 */


public class CustomDrawerAdapter extends RecyclerView.Adapter<CustomDrawerAdapter.ViewHolder> {

    private Context context;
    private List<DrawerItem> drawerItemList;


    public CustomDrawerAdapter(Context context, List<DrawerItem> listItems) {
        this.context = context;
        this.drawerItemList = listItems;

    }
// TODO: 3/18/2019 remove comment
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//
//
//        DrawerItemHolder drawerHolder;
//        View view = convertView;
//
//        if (view == null) {
//            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//            drawerHolder = new DrawerItemHolder();
//
//            view = inflater.inflate(layoutResID, parent, false);
//            drawerHolder.itemName = view.findViewById(R.id.drawer_itemName);
//            drawerHolder.icon = view.findViewById(R.id.drawer_icon);
//            drawerHolder.imageSelectedItem = view.findViewById(R.id.image_selected_item);
//            view.setTag(drawerHolder);
//
//        } else {
//            drawerHolder = (DrawerItemHolder) view.getTag();
//        }
//
//        DrawerItem dItem = drawerItemList.get(position);
//
//        drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
//        drawerHolder.itemName.setText(dItem.getItemName());
//
//        if (position == BaseActivityForDrawer.positionSelectedSideMenu) {
//            drawerHolder.imageSelectedItem.setImageDrawable(view.getResources().getDrawable(R.drawable.selected));
//        } else {
//            drawerHolder.imageSelectedItem.setImageDrawable(null);
//        }
//        return view;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.drawer_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DrawerItem dItem = drawerItemList.get(position);

        holder.drawerIcon.setImageDrawable(context.getResources().getDrawable(dItem.getImgResID()));
        holder.drawerItemName.setText(dItem.getItemName());

        if (position == BaseActivityForDrawer.positionSelectedSideMenu) {
            holder.imageSelectedItem.setImageDrawable(context.getResources().getDrawable(R.drawable.selected));
        } else {
            holder.imageSelectedItem.setImageDrawable(null);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return drawerItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_selected_item)
        ImageView imageSelectedItem;
        @BindView(R.id.drawer_icon)
        ImageView drawerIcon;
        @BindView(R.id.drawer_itemName)
        TextView drawerItemName;
        @BindView(R.id.itemLayout)
        LinearLayout itemLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
