package com.e16din.datamanager.example;

import java.io.Serializable;

public class TestObject implements Serializable {

    private long id = 0;
    private int count = 10;
    private boolean condition = true;
    private String text = "Text";
    private User user = new User("Bruce");

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
