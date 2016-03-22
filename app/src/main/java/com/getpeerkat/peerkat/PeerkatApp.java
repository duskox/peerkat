package com.getpeerkat.peerkat;

import android.app.Application;
import android.provider.Settings;
import android.util.Log;


import com.getpeerkat.peerkat.daggermodules.DaggerDataComponent;
import com.getpeerkat.peerkat.daggermodules.DataComponent;
import com.getpeerkat.peerkat.daggermodules.DataModule;

import timber.log.Timber;

/**
 * Created by dvorkapic on 18/02/16.
 */
public class PeerkatApp extends Application {

    private static DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        dataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule(this))
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportTree());
        }
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            System.out.println(message);

            // Implement a live logging here
        }
    }

    public static DataComponent getDataComponent() {
        return dataComponent;
    }
}
