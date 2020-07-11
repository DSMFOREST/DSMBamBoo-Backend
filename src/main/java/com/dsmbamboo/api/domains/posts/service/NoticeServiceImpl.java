package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.exception.InvalidCategoryException;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.ArticleType;
import com.dsmbamboo.api.domains.users.security.AuthenticationFacade;
import com.dsmbamboo.api.domains.users.security.UserPrincipal;
import com.dsmbamboo.api.domains.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final UserService userService;
    private final ArticleService articleService;
    private final AuthenticationFacade authenticationFacade;
    private final PublishedIdGenerator publishedIdGenerator;

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleService.findAllArticlesByType(ArticleType.NOTICE, pageable);
    }

    @Override
    public Optional<Article> findByNoticeId(Long noticeId) {
        return articleService.findArticleByTypeAndPublishedId(ArticleType.NOTICE, noticeId);
    }

    @Override
    @Transactional
    public Optional<Article> create(CreateArticleRequest request) {
        if (!NoticeService.isContainsNoticeCategory(request.getCategories()))
            throw new InvalidCategoryException();

        Long userId = ((UserPrincipal) authenticationFacade.getAuthentication().getPrincipal()).getId();
        return userService.findById(userId)
                .map(approver -> {
                    long publishedId = publishedIdGenerator.getNextNoticePublishedId();
                    return articleService.create(request, ArticleType.NOTICE, publishedId, approver);
                });
    }

}
