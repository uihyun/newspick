package com.uihyun.newyorktimes.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

public class StoryData {
    @SerializedName("status")
    public String status;
    @SerializedName("results")
    public List<Story> storyList;

    public String getStatus() {
        return status;
    }

    public List<Story> getStoryList() {
        return storyList;
    }
}
