package com.uihyun.newyorktimes.list;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

public class ListViewItem {
    private int type;
    private String imageUrl;
    private String title;
    private String storyLink;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
