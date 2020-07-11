package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

    Page<Article> findAll(Pageable pageable);
    Optional<Article> findByNoticeId(Long noticeId);

    Optional<Article> create(CreateArticleRequest request);
    static boolean isContainsNoticeCategory(List<Long> categories) {
        return categories.contains(Category.NOTICE_ID);
    }

}
