package com.test.classroom.controller;

import com.test.classroom.domain.*;
import com.test.classroom.repository.HistoryRepository;
import com.test.classroom.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.test.classroom.domain.HandActionRequest;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @Autowired
    private HistoryRepository historyRepository;

    @PostMapping("/api/history")
    public List<HistoryInfo> historyRequest(@RequestBody HistoryRequest historyRequest) {
        /*System.out.println(historyRequest.getName());
        System.out.println(historyRequest.getAction());
        System.out.println(historyRequest.getStartDate());
        System.out.println(historyRequest.getEndDate());*/
        List<HistoryInfo> h = historyService.getHistoryInfoByParams(historyRequest.getName(),
                historyRequest.getAction(),
                historyRequest.getStartDate(),
                historyRequest.getEndDate());
        return h;
    }
}