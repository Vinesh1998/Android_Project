package com.app.skilledlabour.models;

import androidx.annotation.NonNull;

public class Query {
    private int id;
    private int cust_id;
    private String cust_name;
    private int emp_id;
    private String emp_name;
    private String msg;
    private String datetime;
    private boolean status;

    public Query(int id, int cust_id, String cust_name, int emp_id, String emp_name, String msg, String datetime, boolean status) {
        this.id = id;
        this.cust_id = cust_id;
        this.cust_name = cust_name;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.msg = msg;
        this.datetime = datetime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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
