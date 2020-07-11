package com.dsmbamboo.api.domains.posts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Invalid document key detected.")
public class InvalidDocumentKeyException extends RuntimeException {
}
