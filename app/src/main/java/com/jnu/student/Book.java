package com.jnu.student;

public class Book {
    private int coverResource;
    private String title;

    public Book(String title,int coverResource) {
        this.coverResource = coverResource;
        this.title = title;
    }

    public int getCoverResource() {
        return coverResource;
    }

    public String getTitle() {
        return title;
    }


}