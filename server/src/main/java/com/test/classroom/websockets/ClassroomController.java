package com.test.classroom.websockets;

import com.test.classroom.students.StudentRepository;
import com.test.classroom.students.StudentStatus;
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
