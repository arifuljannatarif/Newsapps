package com.example.newsapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.newsapp.model.Constants;
import com.example.newsapp.model.NewsStorage;

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        new Constants();
        new NewsStorage().loadnews(0,0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(StartUp.this,NewsFeed.class));
                finish();
            }
        },2000);
    }
}
