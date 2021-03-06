package com.test.classroom.domain;

public class StudentStatus {
    private final String name;
    private final Boolean handRaised;
    private final Boolean disconnected;

    public StudentStatus(String name, Boolean isHandRaised, Boolean disconnected)
    {
        this.name = name;
        this.handRaised = isHandRaised;
        this.disconnected = disconnected;
    }

    public String getName() {
        return name;
    }

    public Boolean getHandRaised() {
        return handRaised;
    }

    public Boolean getDisconnected() {
        return disconnected;
    }
}
