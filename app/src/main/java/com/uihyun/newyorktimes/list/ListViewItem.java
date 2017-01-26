package com.uihyun.newyorktimes.list;

import android.graphics.Bitmap;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

public class ListViewItem {
    private Bitmap image;
    private int type;
    private String imageUrl;
    private String title;
    private String storyLink;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

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

    public String getStoryLink() {
        return storyLink;
    }

    public void setStoryLink(String storyLink) {
        this.storyLink = storyLink;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
