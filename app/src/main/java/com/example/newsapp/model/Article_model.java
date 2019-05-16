package com.example.newsapp.model;

public class Article_model {
    String title,descripton,content,author,time,imagelink,newslink;

    public Article_model(String title, String descripton, String content, String author, String time, String imagelink,String newslink) {
        this.title = title;
        this.descripton = descripton;
        this.content = content;
        this.author = author;
        this.time = time;
        this.imagelink = imagelink;
        this.newslink=newslink;
    }

    public String getNewslink() {
        return newslink;
    }

    public void setNewslink(String newslink) {
        this.newslink = newslink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }
}
