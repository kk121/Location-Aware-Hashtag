package com.krishna.hashtag.utils;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Krishna on 21/09/16.
 */
public class CustomTwitterApiClient extends TwitterApiClient {
  public CustomTwitterApiClient (TwitterSession session) {
    super(session);
  }

  public CustomService getCustomService() {
    return getService(CustomService.class);
  }
}

