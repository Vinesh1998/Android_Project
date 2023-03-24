package com.app.skilledlabour.models;

import androidx.annotation.NonNull;

public class Skill {
    private String id;
    private String name;
    private String img;

    public Skill(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }
    public  Skill(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
