package com.uihyun.newyorktimes.logger;

import android.app.Application;

import org.acra.ACRA;

/**
 * Created by Uihyun on 2017. 1. 27..
 */

public class Logger {

    private static String packageName;
    private static int logLevel;
    private static boolean isDebugReport;
//    private final int LEVEL_INFO = 0;
//    private final int LEVEL_DEBUG = 1;
//    private final int LEVEL_ERROR = 2;

    public static void initialize(Application application, int logLevel, boolean isDebugReport) {
        Logger.logLevel = logLevel;
        Logger.isDebugReport = isDebugReport;

        if (isDebugReport) {
            ACRA.init(application);
            ACRA.getErrorReporter().setEnabled(true);
        }

        packageName = application.getPackageName();
    }

    public static void info(String className, String string) {
        android.util.Log.i(packageName, "LOGGER[INFO] | " + className + " | " + string);
    }

    public static void debug(String className, String string) {
        if (logLevel > 0)
            android.util.Log.d(packageName, "LOGGER[DEBUG] " + className + " | " + string);
    }

    public static void error(String className, String string) {
        if (logLevel > 1) {
            android.util.Log.e(packageName, "LOGGER[ERROR] " + className + " | " + string);
            if (isDebugReport)
                ACRA.getErrorReporter().handleSilentException(new Throwable(className + " | " + string));
        }
    }
}
