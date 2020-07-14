package com.test.classroom.api.actions;

import com.test.classroom.students.Student;
import com.test.classroom.students.StudentRepository;
import com.test.classroom.students.StudentStatus;
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
    private StudentRepository studentRepository;
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/api/hand")
    public void loginRequest(@RequestBody HandActionRequest handActionRequest) {
        Student student = studentRepository.getStudent(handActionRequest.getName());
        if(student != null && student.getToken().equals(handActionRequest.getToken()))
        {
            student.setHandRaised(handActionRequest.isHandRaisen());
            StudentStatus status = new StudentStatus(student.getName(), student.isHandRaised(), false);
            this.template.convertAndSend("/topic/classroom", status);
        }
    }
}