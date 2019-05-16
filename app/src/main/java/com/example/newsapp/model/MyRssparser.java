package com.example.newsapp.model;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.newsapp.NewsFeed;
import com.prof.rssparser.Article;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class MyRssparser extends AsyncTask<Void,Void,Void> {
    int pos,stat=0;
    String url;
    ProgressBar progressBar;
    Context mContext;
    public  static ArrayList<Article_model> temparray=new ArrayList<Article_model>();
    String address= "https://bengali.news18.com/rss/national.xml" ;
    public MyRssparser(String url,int pos,int stat){
          this.url=url;
          this.pos=pos;
          this.stat=stat;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        Parser parser=new Parser();
        parser.onFinish(new OnTaskCompleted() {
            @Override
            public void onTaskCompleted(@NotNull List<Article> list) {
                for(int i=0;i<list.size();i++){
                    Article article=list.get(i);
                    NewsStorage.newsarray.add(new Article_model(article.getTitle(),article.getDescription()
                            ,article.getContent(),article.getAuthor(),article.getPubDate(),article.getImage(),
                            article.getLink()));
                   //System.out.println("article o aidse");
                   // System.out.println("Image link is \n"+article.getImage());
                }
            }
            @Override
            public void onError(@NotNull Exception e) {
                System.out.println("--------------------Error laoding the xml ");
                e.printStackTrace();
            }
        });
        parser.execute(url);

        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // NewsFeed.refreshLayout.setRefreshing(true);
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        NewsStorage.loadnews(pos+1,stat);

    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
