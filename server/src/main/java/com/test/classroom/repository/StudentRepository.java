package com.test.classroom.repository;


import com.test.classroom.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>  {
    List<Student> findByName(String name);
    List<Student> findByNameAndToken(String name, String token);
    @Transactional
    Long deleteByNameAndToken(String name, String token);
}
