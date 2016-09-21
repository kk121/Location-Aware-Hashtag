package com.krishna.hashtag.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna on 21/09/16.
 */
public class SearchResult {
  @SerializedName("statuses")
  public List<Tweets> tweets = new ArrayList<>();
}
