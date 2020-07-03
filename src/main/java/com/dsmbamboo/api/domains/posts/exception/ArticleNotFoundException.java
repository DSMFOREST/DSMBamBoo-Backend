package com.dsmbamboo.api.domains.posts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Article has not found.")
public class ArticleNotFoundException extends RuntimeException {
}
