package com.zeyadh.balancemapper;

import java.util.Date;

public class cashRecord {
    int id;
    String category;
    Double cash;
    String name;
    String comments;
    Date date;

    public cashRecord(int id, String name, Double cash, String category, Date date, String comments) {
        this.id = id;
        this.name = name;
        this.cash = cash;
        this.category = category;
        this.date = date;
        this.comments = comments;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
