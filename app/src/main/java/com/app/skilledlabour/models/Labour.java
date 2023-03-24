package com.app.skilledlabour.models;

import androidx.annotation.NonNull;

public class Labour {
    private String id;
    private String name;
    private String mobile;
    private String email;
    private int age;
    private String skill_set;
    private String availability;
    private String rating;
    private boolean status;

    public Labour() {}
    public Labour(String id, String name, String email, String mobile, int age, String skill_set, String availability,
                  String rating, boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.skill_set = skill_set;
        this.availability = availability;
        this.rating = rating;
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
