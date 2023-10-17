package com.jnu.student;

import java.io.Serializable;

public class Book implements Serializable {
    public Book(String title,int coverResource) {
        this.coverResource = coverResource;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public int getCoverResource(){return  coverResource;}
    public void setTitle(String title){
        this.title=title;
    }

    public void setCoverResource(int coverResource) {
        this.coverResource = coverResource;
    }

    private String title;
    private int coverResource;
}