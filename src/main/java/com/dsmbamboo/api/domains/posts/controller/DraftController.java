package com.dsmbamboo.api.domains.posts.controller;

import com.dsmbamboo.api.domains.posts.dto.DraftResponse;
import com.dsmbamboo.api.domains.posts.exception.ArticleNotFoundException;
import com.dsmbamboo.api.domains.posts.service.DraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/drafts")
@RequiredArgsConstructor
public class DraftController {

    private final DraftService draftService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public Page<DraftResponse> findAll(Pageable pageable) {
        return draftService.findAll(pageable)
                .map(DraftResponse::new);
    }

    @GetMapping("/{articleId}")
    @Secured("ROLE_ADMIN")
    public DraftResponse findByDraftId(@PathVariable @Valid Long articleId) {
        return draftService.findByArticleId(articleId)
                .map(DraftResponse::new)
                .orElseThrow(ArticleNotFoundException::new);
    }

}
