package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.exception.InvalidCategoryException;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.ArticleType;
import com.dsmbamboo.api.domains.users.exception.UserNotFoundException;
import com.dsmbamboo.api.domains.users.security.AuthenticationFacade;
import com.dsmbamboo.api.domains.users.security.JwtTokenProvider;
import com.dsmbamboo.api.domains.users.security.UserPrincipal;
import com.dsmbamboo.api.domains.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DraftServiceImpl implements DraftService {

    private final UserService userService;
    private final ArticleService articleService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationFacade authenticationFacade;
    private final PublishedIdGenerator publishedIdGenerator;

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

    @Override
    public Optional<Article> approve(Long draftId) {
        return articleService.findArticleByTypeAndId(ArticleType.DRAFT, draftId)
                .map(article -> {
                    Long approverId = ((UserPrincipal) authenticationFacade.getAuthentication().getPrincipal()).getId();
                    return userService.findById(approverId)
                            .map(approver -> {
                                long publishedId = publishedIdGenerator.getNextNoticePublishedId();
                                return articleService.save(article.approve(approver, publishedId));
                            })
                            .orElseThrow(UserNotFoundException::new);
                });
    }

    @Override
    public Optional<Article> disapprove(Long draftId) {
        return articleService.findArticleByTypeAndId(ArticleType.DRAFT, draftId)
                .map(article -> {
                    Long approverId = ((UserPrincipal) authenticationFacade.getAuthentication().getPrincipal()).getId();
                    return userService.findById(approverId)
                            .map(approver -> articleService.save(article.disapprove()))
                            .orElseThrow(UserNotFoundException::new);
                });
    }

}
