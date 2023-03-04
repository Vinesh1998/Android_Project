package com.app.skilledlabour.models;

import androidx.annotation.NonNull;

public class Labour {
    private int id;
    private String name;
    private int age;
    private String skill_set;
    private String availability;
    private double rating;
    private boolean status;

    public Labour(int id, String name, int age, String skill_set, String availability,
                  double rating, boolean status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.skill_set = skill_set;
        this.availability = availability;
        this.rating = rating;
        this.status = status;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSkill_set() {
        return skill_set;
    }

    public void setSkill_set(String skill_set) {
        this.skill_set = skill_set;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
