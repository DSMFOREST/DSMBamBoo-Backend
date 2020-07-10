package com.dsmbamboo.api.domains.questions.controller;

import com.dsmbamboo.api.domains.questions.dto.CreateQuestionRequest;
import com.dsmbamboo.api.domains.questions.dto.QuestionResponse;
import com.dsmbamboo.api.domains.questions.service.StudentQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students/questions/")
@RequiredArgsConstructor
public class QuestionController {

    private final StudentQuestionService questionService;

    @PostMapping
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionResponse create(CreateQuestionRequest request) {
        return new QuestionResponse(questionService.create(request));
    }


}
