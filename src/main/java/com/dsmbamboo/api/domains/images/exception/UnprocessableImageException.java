package com.dsmbamboo.api.domains.images.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Unprocessable image detected.")
public class UnprocessableImageException extends RuntimeException {
}
