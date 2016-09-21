package com.krishna.hashtag.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krishna.hashtag.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by Krishna on 21/09/16.
 */
public class FragmentSignIn extends Fragment {
    private TwitterLoginButton loginButton;
    private ActivityCallback activityCallback;

    public static FragmentSignIn newInstance() {
        FragmentSignIn fragment = new FragmentSignIn();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_sign_in, container, false);
        loginButton = (TwitterLoginButton) layout.findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                activityCallback.onSignIn(true);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
                activityCallback.onSignIn(false);
            }
        });
        return layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityCallback = (ActivityCallback) context;
    }

    public void notifyBtnsOnActivityResult(int requestCode, int resultCode, Intent data) {
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    public interface ActivityCallback {
        void onSignIn(boolean success);
    }
}
