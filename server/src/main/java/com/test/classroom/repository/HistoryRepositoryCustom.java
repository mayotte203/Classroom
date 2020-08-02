package com.test.classroom.repository;

import com.test.classroom.domain.History;

import java.util.Date;
import java.util.List;

public interface HistoryRepositoryCustom {
    public List<History> findByParams(String name, String action, Date startDate, Date endDate, Boolean ascending);
}