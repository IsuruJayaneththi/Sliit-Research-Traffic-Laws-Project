package com.example.sliitlawresearchproject.models;

public class ModelData {

    String title, description, uid;

    public ModelData() {
    }

    public ModelData(String title, String description, String uid) {
        this.title = title;
        this.description = description;
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
