package com.example.newsapp;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.model.Constants;
import com.example.newsapp.model.MyRssparser;
import com.example.newsapp.model.NewsStorage;
import com.example.newsapp.model.News_Adapter;

public class NewsFeed extends AppCompatActivity  implements ViewTreeObserver.OnScrollChangedListener {
    public  static SwipeRefreshLayout refreshLayout;
    TextView not_connectedlb;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerview;
    public  static  News_Adapter news_adapter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        refreshLayout=findViewById(R.id.feed_swiperefresh);
               recyclerview=findViewById(R.id.feed_rv);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        news_adapter =new News_Adapter(this);
        recyclerview.setAdapter(news_adapter);
        not_connectedlb=findViewById(R.id.feed_notconnected);
        refreshLayout.setRefreshing(true);
        NewsStorage.loadnews(0,1);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                refreshLayout.setEnabled(linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0); // 0 is for first item position
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsStorage.loadnews(0,1);
            }
        });
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onScrollChanged() {
        System.out.println("scrolling");
    }
}
