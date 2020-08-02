package com.test.classroom.repository;

import com.test.classroom.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryRepository extends JpaRepository<History, Long> {
    //@Query(value="SELECT * FROM history h JOIN data d ON (h.student_id = d.id) WHERE LOWER(d.name) LIKE :name AND h.action LIKE :action AND h.action_date >= :start_date AND h.action_date <= :end_date")
    //List<History> findByParams(@Param("name") String name,@Param("action") String action,@Param("start_date") Date startDate,@Param("end_date") Date endDate);
    //List<History> findByStudent_NameContainsIgnoreCaseAndActionContainsIgnoreCaseAndActionDateGreaterThanEqualAndActionDateLessThanEqualOrderByActionDateAsc(String name, String action, Date startDate, Date endDate);
    //List<History> findByStudent_NameContainsIgnoreCaseAndActionContainsIgnoreCaseAndActionDateGreaterThanEqualAndActionDateLessThanEqualOrderByActionDateDesc(String name, String action, Date startDate, Date endDate);
}