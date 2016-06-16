package com.huixue.study.adapter;

/**
 * Created by Administrator on 2016/6/15.
 * 适配器get和set方法
 */
public class ItemCourse {
    private int itemId;
    private String itemName;
    public ItemCourse(){

    }
    public ItemCourse(int itemId, String itemName){
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
