package com.example.newsapp.model;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.NewsFeed;
import com.example.newsapp.R;
import com.example.newsapp.customtab.MakeTab;
import com.prof.rssparser.Article;

public class Viewholder extends RecyclerView.ViewHolder {
    TextView title,time,author;
    ImageView image;
    public Viewholder(@NonNull View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.rvititle);
        time=itemView.findViewById(R.id.rvitime);
        image=itemView.findViewById(R.id.rviimage);
        author=itemView.findViewById(R.id.rviauthor );
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = getAdapterPosition();
                Article_model article=NewsStorage.newsarray.get(x);
                MakeTab.launchurl(v.getContext(),article.getNewslink());
                /*
                Showfullnews newFragment = new Showfullnews(article.getNewslink(),article.getImagelink());
                FragmentManager f = ((Activity) v.getContext()).getFragmentManager();
                FragmentTransaction ft = f.beginTransaction();
                Fragment prev = f.findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                newFragment.show(ft, "dialog");
               */
            }
        });
    }
}
