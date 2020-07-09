package com.test.classroom;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

    ArrayList<Student> students = new ArrayList<>();

    @PostMapping("/api")
    public LoginInfo loginRequest(@RequestBody LoginRequest loginRequest) {
        Student student = new Student(loginRequest.getName());
        students.add(student);
        return new LoginInfo(student.getName(), student.getToken(), new Boolean(true));
    }
}