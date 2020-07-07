package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.ArticleType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DraftServiceImpl implements DraftService {

    private final ArticleService articleService;

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleService.findAllArticlesByType(ArticleType.DRAFT, pageable);
    }

    @Override
    public Optional<Article> findByArticleId(Long articleId) {
        return articleService.findArticleByTypeAndId(ArticleType.DRAFT, articleId);
    }

}
