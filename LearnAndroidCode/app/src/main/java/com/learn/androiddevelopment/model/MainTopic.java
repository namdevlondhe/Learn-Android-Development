package com.learn.androiddevelopment.model;

public class MainTopic {
    String[] name;
    String[] description;
    String[] url;

    public MainTopic(String[] s, String[] s1, String[] s2) {
        this.name = s;
        this.description = s1;
        this.url = s2;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }
}
