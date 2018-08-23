package com.example.dara.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.dara.newsapp.model.News;
import com.example.dara.newsapp.model.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.ItemClickListener {

    private RecyclerView mRecyclerView;

    private List<News> mList;

    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);

        mList = new ArrayList<>();

        mAdapter = new NewsAdapter(mList, this);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClickListener(News newsItem) {

    }
}
