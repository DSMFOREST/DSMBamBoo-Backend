package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DraftService {

    Page<Article> findAll(Pageable pageable);
    Article create(String documentKey, CreateArticleRequest request);
    Optional<Article> findByArticleId(Long articleId);

    Optional<Article> approve(Long articleId);
    Optional<Article> disapprove(Long articleId);

}
