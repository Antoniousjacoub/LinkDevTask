package com.example.antonio.linkdevtask.dataModel;

/**
 * Created by antonio on 1/16/19.
 */

public class DrawerItem {
    private String ItemName;
    private int imgResID;
    private boolean selected;

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getImgResID() {
        return imgResID;
    }

    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
