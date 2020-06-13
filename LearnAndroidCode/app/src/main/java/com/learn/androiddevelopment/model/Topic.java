package com.learn.androiddevelopment.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class Topic implements Serializable, Comparable<Topic> {
    private int id;
    private String name;
    private String category;
    private List<SubTopic> subTopicList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<SubTopic> getSubTopicList() {
        return subTopicList;
    }

    public void setSubTopicList(List<SubTopic> subTopicList) {
        this.subTopicList = subTopicList;
    }

    @Override
    public int compareTo(Topic o) {
        if (id > o.id) {
            return 1;
        } else if (id < o.id) {
            return -1;
        } else {
            return 0;
        }
    }
}
