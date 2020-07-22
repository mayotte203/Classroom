package com.test.classroom.service;

import com.test.classroom.domain.Student;
import com.test.classroom.domain.StudentStatus;
import com.test.classroom.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class StudentService {
    private ReentrantLock mutex = new ReentrantLock();

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(){
    }

    public synchronized Student addStudent(String name) {
        Student student = null;
        List<Student> studentsList = studentRepository.findByName(name);
        if(studentsList.isEmpty()) {
            student = new Student(name);
            studentRepository.save(student);
        }
        return student;
    }

    public synchronized Boolean setStudentHandRaised(String name, String token, Boolean isHandRaised){
        try {
            mutex.lock();
            List<Student> studentsList = studentRepository.findByNameAndToken(name, token);
            if(!studentsList.isEmpty()){
                studentsList.get(0).setHandRaised(isHandRaised);
                studentRepository.save(studentsList.get(0));
                return true;
            }
        } finally {
            mutex.unlock();
        }
        return false;
    }

    public synchronized Boolean deleteStudent(String name, String token){
        try {
            mutex.lock();
            return(studentRepository.deleteByNameAndToken(name, token) != 0);
        } finally {
            mutex.unlock();
        }
    }

    public synchronized List<StudentStatus> getStudentStatusList()
    {
        List<StudentStatus> studentsStatusList = new ArrayList<>();
        try {
            mutex.lock();
            List<Student> studentsList = studentRepository.findAll();
            for (Student student: studentsList) {
                studentsStatusList.add(new StudentStatus(student.getName(), student.isHandRaised(), false));
            }
        } finally {
            mutex.unlock();
        }
        return studentsStatusList;
    }
}
