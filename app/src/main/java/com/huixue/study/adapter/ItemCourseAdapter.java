package com.huixue.study.adapter;

/**
 * Created by Administrator on 2016/6/15.
 * 适配器get和set方法
 */
public class ItemCourseAdapter {
    private int itemId;
    private String itemName;
    public ItemCourseAdapter(){

    }
    public ItemCourseAdapter(int itemId,String itemName){
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
