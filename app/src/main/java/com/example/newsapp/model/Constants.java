package com.example.newsapp.model;

import android.net.Uri;

import java.util.ArrayList;

public class Constants {


    /*
      "http://www.prothom-alo.com/feed/"  give image
      https://bengali.news18.com/rss/national.xml   without image
      https://news.google.com/rss?bn&hl=bn&gl=BD&ceid=BD:bn  without image
      https://www.thedailystar.net/top-news/rss.xml  with images
      https://www.thedailystar.net/sports/rss.xml   sports

    */
    public static final String API_KEY="220e55e3ce5443e0bf942ca9884977f7";
    public static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";
    public static ArrayList<String> URL_ARRAY=new ArrayList<String>();
    public static String urlnews="https://newsapi.org/v2/everything?q=bitcoin&from=2019-04-14&sortBy=publishedAt&apiK" +
            "ey=220e55e3ce5443e0bf942ca9884977f7";

    public Constants() {
        URL_ARRAY.add("https://www.jugantor.com/feed/rss.xml");
        URL_ARRAY.add("http://www.prothom-alo.com/feed/");
       URL_ARRAY.add("https://www.kalerkantho.com/rss.xml");
        //URL_ARRAY.add("https://bengali.news18.com/rss/national.xml");
        //URL_ARRAY.add("https://www.thedailystar.net/top-news/rss.xml");
        //URL_ARRAY.add("https://news.google.com/rss?bn&hl=bn&gl=BD&ceid=BD:bn");

    }
    public  static void addUrl(String url){
        int fl=0;
        for(String d : URL_ARRAY){
            if(d!= null && d.equals(url))
            {
                fl=1;
                break;
            }
        }
        if(fl==0)URL_ARRAY.add(url);
    }
}
