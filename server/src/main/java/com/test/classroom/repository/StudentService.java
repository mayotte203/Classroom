package com.test.classroom.repository;

import com.test.classroom.domain.Student;
import com.test.classroom.domain.StudentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class StudentService {
    private ArrayList<Student> students = new ArrayList<>();
    private ReentrantLock mutex = new ReentrantLock();

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(){
    }

    public Student addStudent(String name) {
        try {
            mutex.lock();
            for (Student student: students) {
                if(student.getName().equals(name)) {
                    return null;
                }
            }
            Student student = new Student(name);
            students.add(student);
            studentRepository.save(student);
            return student;
        } finally {
            mutex.unlock();
        }
    }

    public Student getStudent(String name){
        Student result = null;
        try {
            mutex.lock();
            for (Student student: students) {
                if(student.getName().equals(name)) {
                    result = student;
                }
            }
        } finally {
            mutex.unlock();
        }
        return result;
    }

    public void deleteStudent(Student student){
        try {
            mutex.lock();
            students.remove(student);
        } finally {
            mutex.unlock();
        }
    }

    public ArrayList<StudentStatus> getStudentStatusList()
    {
        ArrayList<StudentStatus> studentsStatusList = new ArrayList<>();
        try {
            mutex.lock();
            for (Student student: students) {
                studentsStatusList.add(new StudentStatus(student.getName(), student.isHandRaised(), false));
            }
        } finally {
            mutex.unlock();
        }
        return studentsStatusList;
    }
}
