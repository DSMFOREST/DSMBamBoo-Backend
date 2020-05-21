package com.dsmbamboo.api.domains.questions.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentQuestionRepository extends JpaRepository<StudentQuestion, Long> {
}
