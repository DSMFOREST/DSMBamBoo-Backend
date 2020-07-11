package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.exception.InvalidCategoryException;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.ArticleType;
import com.dsmbamboo.api.domains.users.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DraftServiceImpl implements DraftService {

    private final ArticleService articleService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleService.findAllArticlesByType(ArticleType.DRAFT, pageable);
    }

    @Override
    public Article create(String documentKey, CreateArticleRequest request) {
        if (NoticeService.isContainsNoticeCategory(request.getCategories()))
            throw new InvalidCategoryException();
        jwtTokenProvider.validateDocumentKey(documentKey);
        return articleService.create(request, ArticleType.DRAFT, null, null);
    }

    @Override
    public Optional<Article> findByArticleId(Long articleId) {
        return articleService.findArticleByTypeAndId(ArticleType.DRAFT, articleId);
    }

}
