package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.dto.NoticeResponse;
import com.dsmbamboo.api.domains.posts.exception.InvalidCategoryException;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private static final String CATEGORY_NAME = "공지사항";
    private final ArticleService articleService;
    private final PublishedIdGenerator publishedIdGenerator;

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleService.findAllByCategories_Name(CATEGORY_NAME, pageable);
    }

    @Override
    public Optional<Article> findByNoticeId(Long noticeId) {
        return articleService.findByCategories_NameAndPublishedId(CATEGORY_NAME, noticeId);
    }

    @Override
    public NoticeResponse create(CreateArticleRequest request) {
        if (!isContainsNoticeCategory(request.getCategories()))
            throw new InvalidCategoryException();

        Article article = articleService.create(request, publishedIdGenerator.getNextNoticePublishedId());
        return new NoticeResponse(article);
    }

    private boolean isContainsNoticeCategory(List<Long> categories) {
        return categories.contains(Category.NOTICE_ID);
    }

}
