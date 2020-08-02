package com.test.classroom.repository;

import com.test.classroom.domain.History;
import com.test.classroom.domain.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HistoryRepositoryCustomImpl implements HistoryRepositoryCustom{
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public List<History> findByParams(String name, String action, Date startDate, Date endDate, Boolean ascending) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<History> cq = cb.createQuery(History.class);
        Root<History> history = cq.from(History.class);
        Join<History, Student> student = history.join("student");
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(cb.lower(student.get("name")), "%" + name.toLowerCase() + "%"));
        predicates.add(cb.like(history.get("action"), "%" + action + "%"));
        predicates.add(cb.between(history.get("actionDate"), startDate, endDate));
        cq.where(predicates.toArray(new Predicate[0]));
        Order order;
        if(ascending){
            order = cb.asc(history.get("actionDate"));
        }
        else{
            order = cb.desc(history.get("actionDate"));
        }
        cq.orderBy(order);
        return em.createQuery(cq).getResultList();
    }
}
