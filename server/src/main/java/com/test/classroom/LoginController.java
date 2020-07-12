package com.test.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

    private StudentController studentController;
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/api")
    public LoginInfo loginRequest(@RequestBody LoginRequest loginRequest) {
        Student student = studentController.addStudent(loginRequest.getName());
        if(student != null)
        {
            this.template.convertAndSend("/topic/classroom", new StudentStatus(student.getName(), student.isHandRaised(), false));
            return new LoginInfo(student.getName(), student.getToken(), true);
        }
        else
        {
            return new LoginInfo(null, null, false);
        }
    }

    @PostMapping("/api/logout")
    public void logoutRequest(@RequestBody LogoutRequest logoutRequest) {
        Student student = studentController.getStudent(logoutRequest.getName());
        if (student != null && student.getToken().equals(logoutRequest.getToken())) {
            this.template.convertAndSend("/topic/classroom", new StudentStatus(student.getName(), student.isHandRaised(), true));
            studentController.deleteStudent(student);
        }
    }
}