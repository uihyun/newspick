package com.uihyun.newyorktimes.manager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.uihyun.newyorktimes.AppController;
import com.uihyun.newyorktimes.util.GsonBodyRequest;
import com.uihyun.newyorktimes.data.StoryData;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

public class StoryManager {
    private static StoryManager instance;

    public static StoryManager getInstance() {
        if (instance == null) {
            instance = new StoryManager();
        }

        return instance;
    }

    public void readStories(final Response.Listener<StoryData> listener,
                            final Response.ErrorListener errorListener) {

        StringBuilder url = new StringBuilder();
        url.append("https://api.nytimes.com/svc/topstories/v2/home.json?api-key=cf23f0334a174fff975fc2400ccbfdd9");

        GsonBodyRequest<StoryData> request = new GsonBodyRequest<StoryData>(
                Request.Method.GET,
                url.toString(),
                StoryData.class,
                null,
                listener,
                errorListener);

        AppController.getRequestQueue().add(request);
    }
}
