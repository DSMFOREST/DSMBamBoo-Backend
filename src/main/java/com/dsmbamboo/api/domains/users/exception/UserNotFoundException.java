package com.dsmbamboo.api.domains.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User has not found.")
public class UserNotFoundException extends RuntimeException {
}
