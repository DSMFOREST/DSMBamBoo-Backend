package com.dsmbamboo.api.domains.questions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student question has not found.")
public class QuestionNotFoundException extends RuntimeException{
}
