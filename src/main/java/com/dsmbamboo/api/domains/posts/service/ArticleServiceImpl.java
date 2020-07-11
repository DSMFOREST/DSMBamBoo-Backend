package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.images.model.ImageRepository;
import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.ArticleRepository;
import com.dsmbamboo.api.domains.posts.model.ArticleType;
import com.dsmbamboo.api.domains.posts.model.CategoryRepository;
import com.dsmbamboo.api.domains.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Override
    public Page<Article> findAllArticlesByType(ArticleType type, Pageable pageable) {
        return articleRepository.findAllByIsActiveTrueAndType(type, pageable);
    }

    @Override
    public Optional<Article> findArticleByTypeAndPublishedId(ArticleType type, Long publishedId) {
        return articleRepository.findByIsActiveTrueAndTypeAndPublishedId(type, publishedId);
    }

    @Override
    public Optional<Article> findArticleByTypeAndId(ArticleType type, Long articleId) {
        return articleRepository.findByIsActiveTrueAndTypeAndId(type, articleId);
    }

    @Override
    public Article create(CreateArticleRequest request, ArticleType type, Long publishedId, User approver) {
        return articleRepository.save(Article.builder()
                .id(0L)
                .title(request.getTitle())
                .content(request.getContent())
                .type(type)
                .categories(categoryRepository.findAllById(request.getCategories()))
                .images(imageRepository.findAllById(request.getImages()))
                .publishedId(publishedId)
                .approver(approver)
                .isActive(true)
                .facebookLink(null)
                .build());
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

}
