package com.dsmbamboo.api.domains.questions.service;

import com.dsmbamboo.api.domains.questions.dto.CreateDocumentKeyRequest;
import com.dsmbamboo.api.domains.questions.dto.CreateQuestionRequest;
import com.dsmbamboo.api.domains.questions.exception.IncorrectAnswerException;
import com.dsmbamboo.api.domains.questions.exception.QuestionNotFoundException;
import com.dsmbamboo.api.domains.questions.model.StudentQuestion;
import com.dsmbamboo.api.domains.questions.model.StudentQuestionRepository;
import com.dsmbamboo.api.domains.users.security.AuthenticationFacade;
import com.dsmbamboo.api.domains.users.security.JwtTokenProvider;
import com.dsmbamboo.api.domains.users.security.UserPrincipal;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationFacade authenticationFacade;
    private final StudentQuestionRepository questionRepository;

    @Override
    public StudentQuestion create(CreateQuestionRequest request) {
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

    @Override
    public String generateDocumentKey(Long questionId, CreateDocumentKeyRequest request) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    if (!isCorrectAnswer(request.getAnswer(), question.getAnswer()))
                        throw new IncorrectAnswerException();
                    return question;
                })
                .map(question -> {
                    Long userId = ((UserPrincipal) authenticationFacade.getAuthentication().getPrincipal()).getId();
                    return jwtTokenProvider.createDocumentKey(userId.toString());
                })
                .orElseThrow(QuestionNotFoundException::new);
    }

    private boolean isCorrectAnswer(String rawAnswer, String encodedAnswer) {
        return passwordEncoder.matches(rawAnswer, encodedAnswer);
    }

}
