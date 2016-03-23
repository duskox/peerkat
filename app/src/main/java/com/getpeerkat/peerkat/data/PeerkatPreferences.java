package com.getpeerkat.peerkat.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.getpeerkat.peerkat.PeerkatApp;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by dvorkapic on 21/03/16.
 */
public class PeerkatPreferences {

    private final String FIRST_RUN = "first-run";

    private SharedPreferences sharedPreferences;

    @Singleton
    @Inject
    PeerkatPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void firstRunExecuted() {
        sharedPreferences.edit().putBoolean(FIRST_RUN, false).commit();
    }

    public boolean isFirstRun() {
        return sharedPreferences.getBoolean(FIRST_RUN, true);
    }

}
