package com.example.newsapp.model;

import android.os.AsyncTask;
import com.example.newsapp.NewsFeed;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class NewsStorage extends AsyncTask<Void,Void,Void> {
    public  static ArrayList<Article_model> newsarray=new ArrayList<Article_model>();
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        NewsFeed.news_adapter.notifyDataSetChanged();
        NewsFeed.refreshLayout.setRefreshing(false);
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected Void doInBackground(Void... urls) {
        String urlstr="";
        URL url;
        HttpURLConnection urlConnection;
        try {
            url=new URL(Constants.urlnews);
            urlConnection=(HttpURLConnection)url.openConnection();
            String response=NewsStorage.StreamtoSting(urlConnection.getInputStream());
            parseResult(response);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String StreamtoSting(InputStream stream)throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(stream));
        String data,result="";
        while ((data=bufferedReader.readLine())!=null){
            result+=data;
        }
        if(stream!=null)stream.close();
        return result;

    }
    private  void parseResult(String result){
        JSONObject response=null;
        JSONArray articles;
        try {
            response=new JSONObject(result);
            articles=response.optJSONArray("articles");
            for(int i=0;i<articles.length();i++){
                JSONObject article=articles.optJSONObject(i);
                String title,time,author,url,urltoimage,content,description;
                title=article.optString(Constants.KEY_TITLE);
                time=article.optString(Constants.KEY_PUBLISHEDAT);
                author=article.optString(Constants.KEY_AUTHOR);
                url=article.optString(Constants.KEY_URL);
                urltoimage=article.optString(Constants.KEY_URLTOIMAGE);
                description=article.optString(Constants.KEY_DESCRIPTION);
                //content=article.opt(Constants.)
                NewsStorage.newsarray.add(new Article_model(title,description,"contest",author,time ,urltoimage,url));
            }

        }catch (Exception e){
            e.printStackTrace();

        }
    }
    public static void loadnews(int pos,int stat){
        if(pos==0 && stat==1 )MyRssparser.temparray.clear();
        if(pos>Constants.URL_ARRAY.size()-1) {
            NewsStorage.newsarray.addAll(MyRssparser.temparray);
            if(stat==1){
                Collections.shuffle(NewsStorage.newsarray);
                NewsFeed.refreshLayout.setRefreshing(false);
                NewsFeed.news_adapter.notifyDataSetChanged();
            }
            return;
        }
        else{
            new MyRssparser(Constants.URL_ARRAY.get(pos),pos,stat).execute();
        }
    }

    public int getSize(){
        return  newsarray.size()==0 ? 0:newsarray.size();
    }
    public static Object getitem(int pos){
        return  newsarray.get(pos);
    }

}
