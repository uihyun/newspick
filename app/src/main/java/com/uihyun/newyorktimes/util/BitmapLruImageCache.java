package com.uihyun.newyorktimes.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader;

import static android.content.ContentValues.TAG;

/**
 * Created by Uihyun on 2017. 1. 27..
 */

public class BitmapLruImageCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

    public BitmapLruImageCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url) {
        Log.v(TAG, "Retrieved item from Mem Cache");
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        Log.v(TAG, "Added item to Mem Cache");
        put(url, bitmap);
    }
}
