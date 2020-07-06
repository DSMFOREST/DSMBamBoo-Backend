package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.dto.NoticeResponse;
import com.dsmbamboo.api.domains.posts.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NoticeService {

    Page<Article> findAll(Pageable pageable);
    Optional<Article> findByNoticeId(Long noticeId);

    NoticeResponse create(CreateArticleRequest request);

}
