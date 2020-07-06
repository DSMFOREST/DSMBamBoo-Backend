package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticleService {

    Page<Article> findAllByCategories_Name(String categoryName, Pageable pageable);
    Optional<Article> findByCategories_NameAndPublishedId(String categoryName, Long publishedId);

    Article create(CreateArticleRequest request, Long publishedId);

}
