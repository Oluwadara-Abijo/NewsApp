package com.example.dara.newsapp.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.dara.newsapp.model.News;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {


    //Class constructor
    public NewsLoader(@NonNull Context context) {
        super(context);

    }

    /**
     * Invoked by the LoaderManager when the loader starts
     */
    @Override
    protected void onStartLoading() {
        forceLoad();

    }

    /**
     * Connects to the network and makes The Guardian API request on a background thread
     *
     * @return A list of News objects
     */
    @Nullable
    @Override
    public List<News> loadInBackground() {
        String jsonResponse = null;

        URL queryUrl = NetworkUtils.buildQueryUrl();

        try {
            jsonResponse = NetworkUtils.getResponseFromHttpUrl(queryUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return NewsJsonUtils.extractNewsFromJson(jsonResponse);
    }

}
