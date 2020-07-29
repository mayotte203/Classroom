package com.test.classroom.repository;

import com.test.classroom.domain.History;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query(value="SELECT * FROM history h JOIN data d ON (h.student_id = d.id) WHERE h.action_date > '2020-07-24' AND LOWER(d.name) LIKE '%egor%'", nativeQuery = true)
    List<History> findAllHistory();

    @Query(value="SELECT * FROM history h JOIN data d ON (h.student_id = d.id) WHERE LOWER(d.name) LIKE :name AND h.action LIKE :action AND h.action_date >= :start_date AND h.action_date <= :end_date", nativeQuery = true)
    List<History> findByParams(@Param("name") String name,@Param("action") String action,@Param("start_date") Date startDate,@Param("end_date") Date endDate);
}