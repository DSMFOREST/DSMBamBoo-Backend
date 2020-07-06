package com.dsmbamboo.api.domains.posts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid category detected.")
public class InvalidCategoryException extends RuntimeException {
}
