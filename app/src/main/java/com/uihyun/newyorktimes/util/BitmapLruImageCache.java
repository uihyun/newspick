package com.uihyun.newyorktimes.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;
import com.uihyun.newyorktimes.logger.Logger;

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
        Logger.debug(TAG, "Retrieved item from Mem Cache");
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        Logger.debug(TAG, "Added item to Mem Cache");
        put(url, bitmap);
    }
}
