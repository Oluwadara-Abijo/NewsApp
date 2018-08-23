package com.example.dara.newsapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dara.newsapp.model.News;
import com.example.dara.newsapp.model.NewsAdapter;
import com.example.dara.newsapp.utilities.NewsLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.ItemClickListener,
        LoaderManager.LoaderCallbacks<List<News>> {

    private RecyclerView mRecyclerView;

    private ProgressBar mLoadingIndicator;

    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);

        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        List<News> mList = new ArrayList<>();

        mAdapter = new NewsAdapter(mList, this);

        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onItemClickListener(News newsItem) {

    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {

        mLoadingIndicator.setVisibility(View.VISIBLE);
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
        mLoadingIndicator.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()) {
            Log.d(">>>>", "data" + data);
            mAdapter = new NewsAdapter(data, this);
            mRecyclerView.setAdapter(mAdapter);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {

    }
}
