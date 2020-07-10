package com.dsmbamboo.api.domains.questions.service;

import com.dsmbamboo.api.domains.questions.dto.CreateQuestionRequest;
import com.dsmbamboo.api.domains.questions.model.StudentQuestion;
import com.dsmbamboo.api.domains.questions.model.StudentQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentQuestionServiceImpl implements StudentQuestionService {

    private final PasswordEncoder passwordEncoder;
    private final StudentQuestionRepository questionRepository;

    @Override
    public StudentQuestion create(CreateQuestionRequest request) {
        log.warn("QUESTION: " + request.getQuestion());
        log.warn("ANSWER: " + request.getAnswer());
        StudentQuestion question = StudentQuestion.builder()
                .id(0L)
                .question(request.getQuestion())
                .answer(passwordEncoder.encode(request.getAnswer()))
                .isActive(true)
                .build();
        return questionRepository.save(question);
    }

    @Override
    public Optional<StudentQuestion> findByRandomId() {
        return questionRepository.findByRandomId();
    }

}
