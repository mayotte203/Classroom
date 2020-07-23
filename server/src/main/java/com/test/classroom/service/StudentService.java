package com.test.classroom.service;

import com.test.classroom.domain.Student;
import com.test.classroom.domain.StudentStatus;
import com.test.classroom.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentService(){
    }

    public synchronized Student loginStudent(String name) {
        Student student = null;
        List<Student> studentsList = studentRepository.findByName(name);
        if(studentsList.isEmpty()) {
            student = new Student(name);
            studentRepository.save(student);
        }
        else {
            if(!studentsList.get(0).isLogedIn()) {
                student = studentsList.get(0);
                student.setLogedIn(true);
                studentRepository.save(student);
            }
        }
        return student;
    }

    public synchronized Boolean setStudentHandRaised(String name, String token, Boolean isHandRaised){
        List<Student> studentsList = studentRepository.findByName(name);
        if(!studentsList.isEmpty()){
            studentsList.get(0).setHandRaised(isHandRaised);
            studentRepository.save(studentsList.get(0));
            return true;
        }
        return false;
    }

    public synchronized Boolean logoutStudent(String name){
        List<Student> studentsList = studentRepository.findByName(name);
        if(!studentsList.isEmpty()){
            studentsList.get(0).setLogedIn(false);
            studentRepository.save(studentsList.get(0));
            return true;
        }
        return false;
    }

    public synchronized Boolean deleteStudent(String name){
        return(studentRepository.deleteByName(name) != 0);
    }

    public synchronized List<StudentStatus> getStudentStatusList()
    {
        List<StudentStatus> studentsStatusList = new ArrayList<>();
        List<Student> studentsList = studentRepository.findAll();
        for (Student student: studentsList) {
            if (student.isLogedIn()) {
                studentsStatusList.add(new StudentStatus(student.getName(), student.isHandRaised(), false));
            }
        }
        return studentsStatusList;
    }
}
