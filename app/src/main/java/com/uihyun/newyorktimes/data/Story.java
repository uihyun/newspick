package com.uihyun.newyorktimes.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

public class Story {

    @SerializedName("section")
    String section;
    @SerializedName("title")
    String title;
    @SerializedName("url")
    String link;
    @SerializedName("multimedia")
    List<Photo> photoList;

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }
}
