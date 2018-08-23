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

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    /**
     * Called to perform the actual load and return the result of the load operation
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
