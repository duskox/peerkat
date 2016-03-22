package com.getpeerkat.peerkat.daggermodules;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.getpeerkat.peerkat.PeerkatApp;
import com.getpeerkat.peerkat.R;
import com.getpeerkat.peerkat.data.PeerkatPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dvorkapic on 21/03/16.
 */
@Module
public class DataModule {

    private PeerkatApp peerkatApp;

    public DataModule(PeerkatApp app) {
        this.peerkatApp = app;
    }

    @Singleton
    @Provides
    PeerkatApp provideApp() {
        return peerkatApp;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(PeerkatApp peerkatApp) {
        return peerkatApp.getSharedPreferences(peerkatApp.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
    }
}
