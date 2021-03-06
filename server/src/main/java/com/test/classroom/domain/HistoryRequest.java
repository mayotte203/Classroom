package com.test.classroom.domain;

import java.sql.Date;

public class HistoryRequest {
    private String name;
    private String action;
    private Date startDate;
    private Date endDate;
    private Boolean ascending;

    public String getName() {
        return name;
    }

    public String getAction() {
        return action;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Boolean isAscending(){
        return ascending;
    }
}
