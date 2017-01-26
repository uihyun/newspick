package com.uihyun.newyorktimes;

import android.support.multidex.MultiDexApplication;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.uihyun.newyorktimes.logger.Logger;
import com.uihyun.newyorktimes.util.BitmapLruImageCache;

import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by Uihyun on 2017. 1. 26..
 */

@ReportsCrashes
        (
                formKey = "",
                resToastText = R.string.crash_toast_text,
                mode = ReportingInteractionMode.DIALOG,
                resDialogIcon = android.R.drawable.ic_dialog_info,
                resDialogTitle = R.string.crash_dialog_title,
                resDialogText = R.string.crash_dialog_text,
                resDialogOkToast = R.string.crash_dialog_ok_toast,
                customReportContent = {ReportField.APP_VERSION_CODE,
                        ReportField.APP_VERSION_NAME,
                        ReportField.ANDROID_VERSION,
                        ReportField.PHONE_MODEL,
                        ReportField.CUSTOM_DATA,
                        ReportField.STACK_TRACE,
                        ReportField.LOGCAT,
                        ReportField.USER_APP_START_DATE,
                },
                mailTo = "uihyunkei@gmail.com"
        )

public class AppController extends MultiDexApplication {

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

        Logger.initialize(this, Integer.parseInt(getString(R.string.logLevel)), this.getString(R.string.hasDebugReport).equals("1"));
        Logger.info(getClass().getSimpleName(), "start application");

        mRequestQueue = Volley.newRequestQueue(this);

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int maxSize = maxMemory / 8;
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruImageCache(maxSize));
    }
}
