package com.dsmbamboo.api.domains.images.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Image has not found.")
public class ImageNotFoundException extends RuntimeException {
}
