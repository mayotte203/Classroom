package com.test.classroom.service;

import com.test.classroom.domain.History;
import com.test.classroom.domain.HistoryInfo;
import com.test.classroom.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;

    public List<HistoryInfo> getHistoryInfoByParams(String name, String action, Date startDate, Date endDate, boolean ascending){
        List<HistoryInfo> result = new ArrayList<>();
        String nameParam = name.toLowerCase();
        String actionParam = action.toLowerCase();
        /*Sort sort = Sort.by("action_date");
        if(ascending){
            sort.ascending();
        }
        else{
            sort.descending();
        }*/
        List<History> historyList = new ArrayList<>();
        if(ascending){
            historyList = historyRepository.findByStudent_NameContainsIgnoreCaseAndActionContainsIgnoreCaseAndActionDateGreaterThanEqualAndActionDateLessThanEqualOrderByActionDateAsc(nameParam, actionParam, startDate, endDate);
        }
        else{
            historyList = historyRepository.findByStudent_NameContainsIgnoreCaseAndActionContainsIgnoreCaseAndActionDateGreaterThanEqualAndActionDateLessThanEqualOrderByActionDateDesc(nameParam, actionParam, startDate, endDate);
        }
        for (History history: historyList) {
            result.add(new HistoryInfo(history.getStudent().getName(), history.getAction(), history.getActionDate()));
        }
        return result;
    }
}
