package com.example.todolist;

import java.io.Serializable;

public class Notes implements Serializable {
    private int id;
    private String title;
    private String body;

    public Notes(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Notes(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
