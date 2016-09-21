package com.krishna.hashtag.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Krishna on 21/09/16.
 */
public class Media {
    @SerializedName("media_url")
    public String mediaUrl;

    @Override
    public String toString() {
        return "Media{" +
                "mediaUrl='" + mediaUrl + '\'' +
                '}';
    }
}
