package com.dsmbamboo.dsmforest.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NoticeRepository extends PagingAndSortingRepository<Notice, Long> {

    List<Notice> findAllById(long id, Pageable pageable);
}
