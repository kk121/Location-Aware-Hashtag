package com.krishna.hashtag.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.krishna.hashtag.R;
import com.krishna.hashtag.activities.MainActivity;
import com.krishna.hashtag.models.Media;
import com.krishna.hashtag.models.SearchResult;
import com.krishna.hashtag.models.Tweets;
import com.krishna.hashtag.utils.CustomAdapter;
import com.krishna.hashtag.utils.CustomService;
import com.krishna.hashtag.utils.CustomTwitterApiClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Krishna on 21/09/16.
 */
public class FragmentSearch extends Fragment implements View.OnClickListener {
    private static final String TAG = FragmentSearch.class.getSimpleName();
    private static final String FILTER_IMAGE = " filter:images";
    private GridView gridView;
    private CustomAdapter customAdapter;
    private EditText etSearch;
    private ImageButton btnSearch;
    private ArrayList<Media> imagesList;
    private ProgressBar progressBar;

    public static Fragment newInstance() {
        Fragment fragment = new FragmentSearch();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_search, container, false);
        gridView = (GridView) layout.findViewById(R.id.grid_view);
        etSearch = (EditText) layout.findViewById(R.id.tv_search);
        progressBar = (ProgressBar) layout.findViewById(R.id.progressBar);
        btnSearch = (ImageButton) layout.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);
        imagesList = new ArrayList<>();
        customAdapter = new CustomAdapter(getContext(), imagesList, R.layout.item_image);
        gridView.setAdapter(customAdapter);
        return layout;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_search) {
            fetchImagesWithHashTag(etSearch.getText().toString());
        }
    }

    public void fetchImagesWithHashTag(String hashTag) {
        if (hashTag.length() == 0) {
            Toast.makeText(getContext(), R.string.err_blank_search, Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        String tagQuery = hashTag.startsWith("#") ? hashTag : ("#" + hashTag);
        tagQuery = tagQuery + FILTER_IMAGE;
        String location = getLocation("600km");
        CustomTwitterApiClient
                twitterApiClient = new CustomTwitterApiClient(Twitter.getInstance().core.getSessionManager().getActiveSession());
        CustomService customService = twitterApiClient.getCustomService();
        String encodedQuery = URLEncoder.encode(tagQuery);
        Call<SearchResult> call = customService.getTweetList(encodedQuery, location);

        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void success(Result<SearchResult> result) {
                progressBar.setVisibility(View.GONE);
                List<Tweets> tweetsList = result.data.tweets;
                imagesList.clear();
                for (Tweets tweet : tweetsList)
                    imagesList.addAll(tweet.entities.media);
                customAdapter.notifyDataSetChanged();
                if (imagesList.size() == 0)
                    Toast.makeText(getContext(), R.string.err_no_images_found, Toast.LENGTH_SHORT).show();
                Log.i(TAG, imagesList.toString());
            }

            @Override
            public void failure(TwitterException exception) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error occured: " + exception.getCause(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "failure: " + exception.getMessage());
            }
        });
    }

    private String getLocation(String radius) {
        SharedPreferences preferences = getContext().getApplicationContext().getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
        String loc = preferences.getString(MainActivity.KEY_LOCATION, "");
        if (loc.length() > 0) {
            loc = loc + "," + radius;
        }
        Log.i(TAG, "loc: " + loc);
        return loc;
    }
}
