package com.test.classroom.domain;

import com.test.classroom.repository.HistoryRepository;
import com.test.classroom.repository.StudentRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "data")
public class Student implements Serializable{

    final private static Integer maxHistorySize = 10;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "data_generator")
    @SequenceGenerator(name="data_generator", sequenceName = "data_seq", allocationSize=50)
    @Column(name="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "hand_raised")
    private Boolean handRaised;

    @Column(name = "loged_in")
    private Boolean logedIn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    List<History> history = new ArrayList<>();

    public Student() {
    }

    public Student(String name) {
        this.name = name;
        this.handRaised = false;
        setLogedIn(true);
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

    private void addToHistory(String action){
        if(history.size() > maxHistorySize - 1){
            history.remove(0);
        }
        history.add(new History(this, action));
    }

    public void setHandRaised(Boolean isHandRaised){
        if(isHandRaised){
            addToHistory("hand up");
        }
        else {
            addToHistory("hand down");
        }
        this.handRaised = isHandRaised;
    }

    public void setLogedIn(Boolean isLogedIn){
        if(isLogedIn){
            addToHistory("login");
        }
        else {
            addToHistory("logout");
        }
        this.logedIn = isLogedIn;
    }
}
