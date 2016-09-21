package com.krishna.hashtag;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Krishna on 21/09/16.
 */
public class MyApplication extends Application {
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "RS83tvlYu5vRv90PbAVChL9nm";
    private static final String TWITTER_SECRET = "bnKt6n7jqKOhokdCRtwsS2lpUx8LeEdQNZfAWwpWjiQ5vnuzQT";
    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }
}
