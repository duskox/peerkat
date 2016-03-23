package com.getpeerkat.peerkat.daggermodules;

import com.getpeerkat.peerkat.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dvorkapic on 21/03/16.
 */
@Component(
        modules = DataModule.class
)
@Singleton
public interface DataComponent {
        void inject(MainActivity activity);
}
