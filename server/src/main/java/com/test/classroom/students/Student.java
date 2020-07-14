package com.test.classroom.students;

import java.util.Base64;
import java.util.Random;

public class Student {
    private final String name;
    private final String token;
    private Boolean handRaised;

    public Student(String name)
    {
        this.name = name;
        this.handRaised = false;
        byte[] array = new byte[24];
        new Random().nextBytes(array);
        this.token = new String(Base64.getEncoder().encode(array));
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public Boolean isHandRaised() {
        return this.handRaised;
    }

    public void setHandRaised(Boolean isHandRaised){
        this.handRaised = isHandRaised;
    }
}
