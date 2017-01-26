package com.uihyun.newyorktimes;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.uihyun.newyorktimes.util.BitmapLruImageCache;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

public class AppController extends Application {

    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;

    public static RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public static ImageLoader getImageLoader() {
        return mImageLoader;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this);

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int maxSize = maxMemory / 8;
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruImageCache(maxSize));
    }
}
