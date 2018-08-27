package com.example.dara.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dara.newsapp.model.News;
import com.example.dara.newsapp.model.NewsAdapter;
import com.example.dara.newsapp.utilities.NewsLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.ItemClickListener,
        LoaderManager.LoaderCallbacks<List<News>> {

    //UI Elements
    private RecyclerView mRecyclerView;
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessageTextView;

    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);

        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        mErrorMessageTextView = findViewById(R.id.tv_error_message);

        List<News> mList = new ArrayList<>();

        mAdapter = new NewsAdapter(mList, this);

        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        startLoading();

    }

    private void startLoading() {
        //Start loading
        if (isNetworkAvailable()) {
            getSupportLoaderManager().initLoader(0, null, this);
        } else {
            mLoadingIndicator.setVisibility(View.GONE);
            showError();
            mErrorMessageTextView.setText(R.string.error_internet_connection);
        }
    }

    //Checks for internet connectivity
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private void showData() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorMessageTextView.setVisibility(View.GONE);
    }

    private void showError() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClickListener(News newsItem) {
        String webUrl = newsItem.getWebUrl();
        openNewsWebPage(webUrl);

    }

    private void openNewsWebPage(String url) {
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        mLoadingIndicator.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()) {
            mAdapter = new NewsAdapter(data, this);
            mRecyclerView.setAdapter(mAdapter);
            showData();
        } else {
            showError();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {

    }


}
