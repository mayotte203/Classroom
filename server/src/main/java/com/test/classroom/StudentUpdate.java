package com.test.classroom;


public class StudentUpdate {

    private String name;
    private String action;

    public StudentUpdate() {
    }

    public StudentUpdate(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public String getAction() {
        return action;
    }
}