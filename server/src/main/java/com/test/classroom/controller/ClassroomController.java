package com.test.classroom.controller;

import com.test.classroom.repository.StudentRepository;
import com.test.classroom.domain.StudentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ClassroomController {
    @Autowired
    private StudentRepository studentRepository;

    @MessageMapping("/signin")
    @SendToUser("/topic/classroom")
    public ArrayList<StudentStatus> processMessageFromClient(Principal principal) throws Exception {
        return studentRepository.getStudentStatusList();
    }
}
