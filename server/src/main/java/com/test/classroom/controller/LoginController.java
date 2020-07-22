package com.test.classroom.controller;

import com.test.classroom.domain.LoginInfo;
import com.test.classroom.domain.LoginRequest;
import com.test.classroom.domain.LogoutRequest;
import com.test.classroom.domain.Student;
import com.test.classroom.service.StudentService;
import com.test.classroom.domain.StudentStatus;
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
        Student student = studentService.addStudent(loginRequest.getName());
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
        if (studentService.deleteStudent(logoutRequest.getName(), logoutRequest.getToken())) {
            this.template.convertAndSend("/topic/classroom", new StudentStatus(logoutRequest.getName(), false, true));
        }
    }
}