package com.uihyun.newyorktimes.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

public class Photo {
    @SerializedName("url")
    String imageUrl;
    @SerializedName("height")
    int height;
    @SerializedName("width")
    int width;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
