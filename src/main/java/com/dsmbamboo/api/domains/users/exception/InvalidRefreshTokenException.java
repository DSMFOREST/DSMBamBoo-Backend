package com.dsmbamboo.api.domains.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Invalid refresh token.")
public class InvalidRefreshTokenException extends RuntimeException {
}
