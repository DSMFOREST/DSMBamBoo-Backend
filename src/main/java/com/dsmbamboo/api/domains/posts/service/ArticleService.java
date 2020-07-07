package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.ArticleType;
import com.dsmbamboo.api.domains.users.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticleService {

    Page<Article> findAllArticlesByType(ArticleType type, Pageable pageable);

    Optional<Article> findArticleByTypeAndPublishedId(ArticleType type, Long publishedId);
    Optional<Article> findArticleByTypeAndId(ArticleType type, Long id);

    Article create(CreateArticleRequest request, ArticleType type, Long publishedId, User approver);

}
