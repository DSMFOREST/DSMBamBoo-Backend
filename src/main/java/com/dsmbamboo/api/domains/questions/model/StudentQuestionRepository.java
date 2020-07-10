package com.dsmbamboo.api.domains.questions.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentQuestionRepository extends JpaRepository<StudentQuestion, Long> {

    @Query(value = "SELECT * FROM student_question ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<StudentQuestion> findByRandomId();

}
