package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public Page<Article> findAllByCategories_Name(String categoryName, Pageable pageable) {
        return articleRepository.findAllByCategories_Name(categoryName, pageable);
    }

}
