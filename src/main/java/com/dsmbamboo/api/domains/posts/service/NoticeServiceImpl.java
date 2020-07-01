package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private static final String CATEGORY_NAME = "공지사항";
    private final ArticleService articleService;

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleService.findAllByCategories_Name(CATEGORY_NAME, pageable);
    }

    @Override
    public Optional<Article> findByNoticeId(Long noticeId) {
        return articleService.findByCategories_NameAndPublishedId(CATEGORY_NAME, noticeId);
    }

}
