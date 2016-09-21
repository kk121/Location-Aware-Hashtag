package com.krishna.hashtag.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krishna on 21/09/16.
 */
public class Entities {
  @SerializedName ("media")
  public List<Media> media = new ArrayList<>();
}
