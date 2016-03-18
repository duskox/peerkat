package com.getpeerkat.peerkat;

import android.app.Application;
import android.provider.Settings;
import android.util.Log;


import timber.log.Timber;

/**
 * Created by dvorkapic on 18/02/16.
 */
public class PeerkatApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        CrashlyticsCore crashlyticsCore = new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build();
//        Fabric.with(this, new Crashlytics.Builder().core(crashlyticsCore).build());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportTree());
        }
    }

    /** A tree which logs important information for crash reporting. */
//    private static class CrashReportingTree extends Timber.Tree {
//        @Override protected void log(int priority, String tag, String message, Throwable t) {
//            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
//                return;
//            }
//
//            //FakeCrashLibrary.log(priority, tag, message);
//            Crashlytics.log(message);
//
//            if (t != null) {
//                /*if (priority == Log.ERROR) {
//                    FakeCrashLibrary.logError(t);
//                } else if (priority == Log.WARN) {
//                    FakeCrashLibrary.logWarning(t);
//                }*/
//                Crashlytics.logException(t);
//            }
//        }
//    }

    private static class CrashReportTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            System.out.println("Crashlytics sucks");
        }
    }
}
