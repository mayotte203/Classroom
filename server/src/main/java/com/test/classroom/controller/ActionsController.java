package com.test.classroom.controller;

import com.test.classroom.domain.HandActionRequest;
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
public class ActionsController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/api/hand")
    public void loginRequest(@RequestBody HandActionRequest handActionRequest) {
        if(studentService.setStudentHandRaised(handActionRequest.getName(), handActionRequest.getToken(), handActionRequest.isHandRaised()))
        {
            StudentStatus status = new StudentStatus(handActionRequest.getName(), handActionRequest.isHandRaised(), false);
            this.template.convertAndSend("/topic/classroom", status);
        }
    }
}