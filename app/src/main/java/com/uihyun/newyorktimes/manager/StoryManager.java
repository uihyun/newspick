package com.uihyun.newyorktimes.manager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.uihyun.newyorktimes.AppController;
import com.uihyun.newyorktimes.data.StoryData;
import com.uihyun.newyorktimes.logger.Logger;
import com.uihyun.newyorktimes.util.GsonBodyRequest;

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

        String url = "https://api.nytimes.com/svc/topstories/v2/home.json?api-key=cf23f0334a174fff975fc2400ccbfdd9";
        Logger.debug(getClass().getSimpleName(), "url = " + url.toString());

        GsonBodyRequest<StoryData> request = new GsonBodyRequest<>(
                Request.Method.GET,
                url,
                StoryData.class,
                null,
                listener,
                errorListener);

        AppController.getRequestQueue().add(request);
    }
}
