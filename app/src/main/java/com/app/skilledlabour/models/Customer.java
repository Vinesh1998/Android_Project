package com.app.skilledlabour.models;

import androidx.annotation.NonNull;

public class Customer {
    private int id;
    private String name;
    private String contact;
    private String address;
    private int age;
    private boolean status;

    public Customer(int id, String name, String contact, String address, int age, boolean status) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.age = age;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
