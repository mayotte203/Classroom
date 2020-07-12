package com.test.classroom;

import java.util.ArrayList;

public class StudentController {
    private static ArrayList<Student> students = new ArrayList<>();

    public static Student addStudent(String name) {
        for (Student student: students) {
            if(student.getName().equals(name)) {
                return null;
            }
        }
        Student student = new Student(name);
        students.add(student);
        return student;
    }

    public static Student getStudent(String name){
        for (Student student: students) {
            if(student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public static void deleteStudent(Student student){
        students.remove(student);
    }

    public static ArrayList<StudentStatus> getStudentStatusList()
    {
        ArrayList<StudentStatus> studentsStatusList = new ArrayList<>();
        for (Student student: students) {
            studentsStatusList.add(new StudentStatus(student.getName(), student.isHandRaised(), false));
        }
        return studentsStatusList;
    }
}
