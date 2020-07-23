package com.test.classroom.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.Random;

@Entity
@Table(name = "data")
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "hand_raised")
    private Boolean handRaised;

    @Column(name = "loged_in")
    private Boolean logedIn;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "student")
    private History history;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
        this.handRaised = false;
        this.logedIn = true;
        this.history = new History();
        this.history.setStudent(this);
        this.history.addAction("login");
    }

    public String getName() {
        return name;
    }

    public Boolean isHandRaised() {
        return this.handRaised;
    }

    public Boolean isLogedIn(){
        return this.logedIn;
    }

    public void setHandRaised(Boolean isHandRaised){
        if(isHandRaised){
            this.history.addAction("hand up");
        }
        else {
            this.history.addAction("hand down");
        }
        this.handRaised = isHandRaised;
    }

    public void setLogedIn(Boolean isLogedIn){
        if(isLogedIn){
            this.history.addAction("login");
        }
        else {
            this.history.addAction("logout");
        }
        this.logedIn = isLogedIn;
    }
}
