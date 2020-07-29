package com.test.classroom.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Table(name = "history")
public class History implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "history_generator")
    @SequenceGenerator(name="history_generator", sequenceName = "history_seq", allocationSize=50)
    @Column(name="id")
    private Integer id;

    @ManyToOne(/*fetch = FetchType.LAZY*/)
    @JoinColumn(name = "student_id")
    Student student;

    @Column(name = "action_date")
    private Date actionDate;

    @Column(name = "action")
    private String action;

    public History(){
    }

    public History(Student student, String action){
        actionDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        this.student = student;
        this.action = action;
    }

    public Student getStudent() {
        return student;
    }

    public String getAction() {
        return action;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setAction(String action){
        this.action = action;
    }
}