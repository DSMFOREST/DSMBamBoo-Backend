package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.NoticeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {

    Page<NoticeResponse> findAll(Pageable pageable);

}
