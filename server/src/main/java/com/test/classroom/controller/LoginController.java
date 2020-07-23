package com.test.classroom.controller;

import com.test.classroom.domain.*;
import com.test.classroom.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/api")
    public LoginInfo loginRequest(@RequestBody LoginRequest loginRequest) {
        Student student = studentService.loginStudent(loginRequest.getName());
        if(student != null)
        {
            this.template.convertAndSend("/topic/classroom", new StudentStatus(student.getName(), student.isHandRaised(), false));
            return new LoginInfo(student.getName(), true);
        }
        else
        {
            return new LoginInfo(null, false);
        }
    }

    @PostMapping("/api/logout")
    public void logoutRequest(@RequestBody LogoutRequest logoutRequest) {
        if (studentService.logoutStudent(logoutRequest.getName())) {
            this.template.convertAndSend("/topic/classroom", new StudentStatus(logoutRequest.getName(), false, true));
        }
    }
}