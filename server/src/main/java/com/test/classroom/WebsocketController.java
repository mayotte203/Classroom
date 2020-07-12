package com.test.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class WebsocketController {

    private StudentController studentController;
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hand")
    public void handHandler(StudentUpdate update) throws Exception {
        Student student = studentController.getStudent(update.getName());
        if(student != null && student.getToken().equals(update.getToken()))
        {
            student.setHandRaised(update.getHandRaised());
            StudentStatus status = new StudentStatus(student.getName(), student.isHandRaised(), false);
            this.template.convertAndSend("/topic/classroom", status);
        }
    }

    @MessageMapping("/signin")
    @SendToUser("/topic/classroom")
    public ArrayList<StudentStatus> processMessageFromClient(Principal principal) throws Exception {
        return studentController.getStudentStatusList();
    }
}
