package com.test.classroom.domain;

import com.test.classroom.utils.StringToListConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "history")
public class History implements Serializable{
    @Id
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "actions")
    @Convert(converter = StringToListConverter.class)
    private List<String> actions = new ArrayList<>();

    public History(){
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void addAction(String action){
        actions.add(action);
        if(actions.size() > 10){
            actions.remove(0);
        }
    }
}