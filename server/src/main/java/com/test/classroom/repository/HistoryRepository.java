package com.test.classroom.repository;

import com.test.classroom.domain.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;



public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByStudent_NameContainsIgnoreCaseAndActionContainsIgnoreCaseAndActionDateGreaterThanEqualAndActionDateLessThanEqualOrderByActionDateAsc(String name, String action, Date startDate, Date endDate);
    List<History> findByStudent_NameContainsIgnoreCaseAndActionContainsIgnoreCaseAndActionDateGreaterThanEqualAndActionDateLessThanEqualOrderByActionDateDesc(String name, String action, Date startDate, Date endDate);
}