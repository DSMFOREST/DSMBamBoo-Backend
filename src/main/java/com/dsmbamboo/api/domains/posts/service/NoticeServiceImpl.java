package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.NoticeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private static final String CATEGORY_NAME = "공지사항";
    private final ArticleService articleService;

    @Override
    public Page<NoticeResponse> findAll(Pageable pageable) {
        return articleService.findAllByCategories_Name(CATEGORY_NAME, pageable)
                .map(NoticeResponse::new);
    }

}
