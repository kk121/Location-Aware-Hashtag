package com.krishna.hashtag.utils;

import com.krishna.hashtag.models.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Krishna on 21/09/16.
 */
public interface CustomService {
  @GET ("/1.1/search/tweets.json")
  Call<SearchResult> getTweetList (@Query (value = "q", encoded = true) String hashTag, @Query ("geocode") String geoCode);

}
