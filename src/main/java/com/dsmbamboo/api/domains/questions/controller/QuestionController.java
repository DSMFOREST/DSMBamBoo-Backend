package com.dsmbamboo.api.domains.questions.controller;

import com.dsmbamboo.api.domains.questions.dto.CreateDocumentKeyRequest;
import com.dsmbamboo.api.domains.questions.dto.DocumentKeyResponse;
import com.dsmbamboo.api.domains.questions.dto.CreateQuestionRequest;
import com.dsmbamboo.api.domains.questions.dto.QuestionResponse;
import com.dsmbamboo.api.domains.questions.exception.QuestionNotFoundException;
import com.dsmbamboo.api.domains.questions.service.StudentQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/students/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final StudentQuestionService questionService;

    @PostMapping
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionResponse create(@RequestBody @Valid CreateQuestionRequest request) {
        return new QuestionResponse(questionService.create(request));
    }

    @GetMapping
    public QuestionResponse findByRandomId() {
        return questionService.findByRandomId()
                .map(QuestionResponse::new)
                .orElseThrow(QuestionNotFoundException::new);
    }

    @PostMapping("/{questionId}/answer")
    public DocumentKeyResponse generateDocumentKey(@PathVariable Long questionId, @RequestBody @Valid CreateDocumentKeyRequest request) {
        return new DocumentKeyResponse(questionService.generateDocumentKey(questionId, request));
    }

}
