package com.test.classroom.domain;

import javax.persistence.*;
import java.util.Base64;
import java.util.Random;

@Entity
@Table(name = "data")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "token")
    private String token;

    @Column(name = "hand_raised")
    private Boolean handRaised;

    public Student() {

    }

    public Student(String name) {
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
