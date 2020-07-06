package com.dsmbamboo.api.domains.posts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Sequence has not found.")
public class SequenceNotFoundException extends RuntimeException {
}
