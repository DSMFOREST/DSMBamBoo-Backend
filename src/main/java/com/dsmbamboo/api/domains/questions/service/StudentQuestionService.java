package com.dsmbamboo.api.domains.questions.service;

import com.dsmbamboo.api.domains.questions.dto.CreateQuestionRequest;
import com.dsmbamboo.api.domains.questions.model.StudentQuestion;

public interface StudentQuestionService {

    StudentQuestion create(CreateQuestionRequest request);

}
