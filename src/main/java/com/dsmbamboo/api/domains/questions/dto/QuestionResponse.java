package com.dsmbamboo.api.domains.questions.dto;

import com.dsmbamboo.api.domains.questions.model.StudentQuestion;
import lombok.Getter;

@Getter
public class QuestionResponse {

    private Long id;

    private String question;

    public QuestionResponse(StudentQuestion question) {
        this.id = question.getId();
        this.question = question.getQuestion();
    }

}
