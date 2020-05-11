package com.dsmbamboo.api.domains.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Expired or invalid JWT token.")
public class InvalidUserAuthenticationException extends RuntimeException {
}
