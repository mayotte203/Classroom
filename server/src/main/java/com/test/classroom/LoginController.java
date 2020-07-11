package com.test.classroom;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

    StudentController studentController;

    @PostMapping("/api")
    public LoginInfo loginRequest(@RequestBody LoginRequest loginRequest) {
        Student student = studentController.addStudent(loginRequest.getName());
        if(student != null)
        {
            return new LoginInfo(student.getName(), student.getToken(), true);
        }
        else
        {
            return new LoginInfo(null, null, false);
        }
    }
}