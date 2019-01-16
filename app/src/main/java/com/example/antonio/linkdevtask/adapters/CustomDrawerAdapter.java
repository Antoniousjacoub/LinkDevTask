package com.example.antonio.linkdevtask.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antonio.linkdevtask.R;
import com.example.antonio.linkdevtask.dataModel.DrawerItem;

import java.util.List;

/**
 * Created by antonio on 1/16/19.
 */


public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

    private Context context;
    private List<DrawerItem> drawerItemList;
    private int layoutResID;
    private int mSelectedItem;

    public CustomDrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems, int mSelectedItem) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
        this.mSelectedItem = mSelectedItem;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {


        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.itemName =  view.findViewById(R.id.drawer_itemName);
            drawerHolder.icon =  view.findViewById(R.id.drawer_icon);
            drawerHolder.imageSelectedItem=view.findViewById(R.id.image_selected_item);
            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();
        }

        DrawerItem dItem = drawerItemList.get(position);

        drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
        drawerHolder.itemName.setText(dItem.getItemName());

        if (position==mSelectedItem)
            drawerHolder.imageSelectedItem.setImageDrawable(view.getResources().getDrawable(R.drawable.selected));
        return view;
    }

    private static class DrawerItemHolder {
        TextView itemName;
        ImageView icon;
        ImageView imageSelectedItem;
    }

}
