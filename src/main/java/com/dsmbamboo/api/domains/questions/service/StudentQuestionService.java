package com.dsmbamboo.api.domains.questions.service;

import com.dsmbamboo.api.domains.questions.dto.CreateQuestionRequest;
import com.dsmbamboo.api.domains.questions.model.StudentQuestion;

import java.util.Optional;

public interface StudentQuestionService {

    StudentQuestion create(CreateQuestionRequest request);
    Optional<StudentQuestion> findByRandomId();

}
