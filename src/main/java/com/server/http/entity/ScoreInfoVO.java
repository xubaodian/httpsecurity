package com.server.http.entity;

public class ScoreInfoVO {
    private String name;
    private String subject;
    private int count;

    public ScoreInfoVO(String name,String subject, int count) {
        this.name = name;
        this.subject = subject;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
