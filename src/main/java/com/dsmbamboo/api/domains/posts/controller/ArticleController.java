package com.dsmbamboo.api.domains.posts.controller;

import com.dsmbamboo.api.domains.posts.dto.ArticleResponse;
import com.dsmbamboo.api.domains.posts.exception.ArticleNotFoundException;
import com.dsmbamboo.api.domains.posts.model.ArticleType;
import com.dsmbamboo.api.domains.posts.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public Page<ArticleResponse> findAll(Pageable pageable) {
        return articleService.findAllArticlesByType(ArticleType.ARTICLE, pageable)
                .map(ArticleResponse::new);
    }

    @GetMapping("/{publishedId}")
    public ArticleResponse findByArticleId(@PathVariable @Valid Long publishedId) {
        return articleService.findArticleByTypeAndPublishedId(ArticleType.ARTICLE, publishedId)
                .map(ArticleResponse::new)
                .orElseThrow(ArticleNotFoundException::new);
    }

}
