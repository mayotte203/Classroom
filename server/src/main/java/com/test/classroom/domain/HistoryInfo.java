package com.test.classroom.domain;

import java.sql.Date;

public class HistoryInfo {
    private String name;
    private String action;
    private Date date;

    public HistoryInfo(String name, String action, Date date){
        this.name = name;
        this.action = action;
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }
}
