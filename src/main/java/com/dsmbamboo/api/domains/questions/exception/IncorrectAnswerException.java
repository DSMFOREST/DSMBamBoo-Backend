package com.dsmbamboo.api.domains.questions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "Incorrect question answer submitted.")
public class IncorrectAnswerException extends RuntimeException {
}
