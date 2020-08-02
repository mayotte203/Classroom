package com.test.classroom.service;

import com.test.classroom.domain.History;
import com.test.classroom.domain.HistoryInfo;
import com.test.classroom.repository.HistoryRepository;
import com.test.classroom.repository.HistoryRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private HistoryRepositoryCustom historyRepositoryCustom;

    public List<HistoryInfo> getHistoryInfoByParams(String name, String action, Date startDate, Date endDate, boolean ascending){
        List<HistoryInfo> result = new ArrayList<>();
        String nameParam = name.toLowerCase();
        String actionParam = action.toLowerCase();
        List<History> historyList = historyRepositoryCustom.findByParams(nameParam, actionParam, startDate, endDate, ascending);
        for (History history: historyList) {
            result.add(new HistoryInfo(history.getStudent().getName(), history.getAction(), history.getActionDate()));
        }
        return result;
    }
}
