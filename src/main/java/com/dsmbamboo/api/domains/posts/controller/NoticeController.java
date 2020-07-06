package com.dsmbamboo.api.domains.posts.controller;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.dto.NoticeResponse;
import com.dsmbamboo.api.domains.posts.exception.ArticleNotFoundException;
import com.dsmbamboo.api.domains.posts.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public Page<NoticeResponse> findAll(Pageable pageable) {
        return noticeService.findAll(pageable)
                .map(NoticeResponse::new);
    }

    @GetMapping("/{noticeId}")
    public NoticeResponse findByNoticeId(@PathVariable @Valid Long noticeId) {
        return noticeService.findByNoticeId(noticeId)
                .map(NoticeResponse::new)
                .orElseThrow(ArticleNotFoundException::new);
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    public NoticeResponse create(@RequestBody @Valid CreateArticleRequest request) {
        return noticeService.create(request);
    }

}
