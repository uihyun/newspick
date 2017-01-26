package com.uihyun.newyorktimes.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

public class Photo {
    @SerializedName("url")
    String imageUrl;
    @SerializedName("format")
    String format;

    public String getFormat() {
        return format;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
